package com.company.fantasyturnedreal.service.league;

import com.company.fantasyturnedreal.dto.league.CreateQuestionRequest;
import com.company.fantasyturnedreal.dto.league.UpdateQuestionRequest;
import com.company.fantasyturnedreal.enums.QuestionStatus;
import com.company.fantasyturnedreal.enums.QuestionType;
import com.company.fantasyturnedreal.enums.Status;
import com.company.fantasyturnedreal.exception.CannotUpdateEntityException;
import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.exception.QuestionCreationOutOfBoundsException;
import com.company.fantasyturnedreal.model.league.Answer;
import com.company.fantasyturnedreal.model.league.Question;
import com.company.fantasyturnedreal.repository.league.QuestionRepository;
import com.company.fantasyturnedreal.service.AbstractService;
import com.company.fantasyturnedreal.service.contestant.ContestantService;
import com.company.fantasyturnedreal.service.season.EpisodeService;
import com.company.fantasyturnedreal.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService extends AbstractService {

    @Autowired
    EpisodeService episodeService;

    @Autowired
    LeagueService leagueService;

    @Autowired
    ContestantService contestantService;

    @Autowired
    ScoreService scoreService;

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
    UserService userService;

    public List<Question> getQuestions(Long leagueId, Long episodeId, QuestionStatus questionStatus) {
        Specification<Question> spec = Specification.where(null);

        if (leagueId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("league").get("leagueId"), leagueId));
        } else {
            throw new NullPointerException("LeagueId must not be null");
        }

        if (episodeId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("episode").get("episodeId"), episodeId));
        }

        if (questionStatus != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), questionStatus));
        }

        return questionRepo.findAll(spec);    }

    public Question getQuestionById(Long questionId) {
        Question foundQuestion = questionRepo.findById(questionId).orElse(null);
        if (foundQuestion == null) {
            throw new DataNotFoundException(String.format("Question with id '%s' does not exist.", questionId));
        }
        return foundQuestion;
    }

    @Transactional
    public List<Question> createQuestions(List<CreateQuestionRequest> createQuestionRequest) {
        List<Question> questions = createQuestionRequest.stream().map(questionFromRequest -> {
            if (questionFromRequest.getQuestionText() == null ||
                    questionFromRequest.getQuestionType() == null ||
                    questionFromRequest.getEndTime() == null ||
                    questionFromRequest.getLeagueId() == null ||
                    questionFromRequest.getPoints() == null) {
                throw new NullPointerException("The following fields cannot be null: questionText, questionType, endTime, leagueId, points");
            }
            Question question = new Question();

            question.setQuestionText(questionFromRequest.getQuestionText());
            question.setQuestionType(questionFromRequest.getQuestionType());
            question.setEndTime(questionFromRequest.getEndTime());
            question.setPoints(questionFromRequest.getPoints());
            question.setLeague(leagueService.getLeagueById(questionFromRequest.getLeagueId()));
            question.setSubmitter(userService.getUserById(questionFromRequest.getSubmitterUserId()));

            if (questionFromRequest.getStartTime() != null) {
                question.setStartTime(questionFromRequest.getStartTime());
            } else {
                question.setStartTime(LocalDateTime.now());
            }
            if (questionFromRequest.getStartTime().isBefore(LocalDateTime.now())) {
                throw new QuestionCreationOutOfBoundsException("A question cannot be created if the start time is before the current time");
            } else {
                question.setStatus(QuestionStatus.INACTIVE);

            }
            if (questionFromRequest.getQuestionType().equals(QuestionType.MULTIPLE_CHOICE)) {
                question.setPotentialAnswers(questionFromRequest.getPotentialAnswers());
            } else if (questionFromRequest.getQuestionType().equals(QuestionType.BOOLEAN)) {
                question.setPotentialAnswers(Arrays.asList("TRUE", "FALSE"));
            } else if (questionFromRequest.getQuestionType().equals(QuestionType.CONTESTANT_PICK)) {
                question.setPotentialAnswers(contestantService.getContestantNamesByStatus(Status.ALIVE));
            } else if (questionFromRequest.getQuestionType().equals(QuestionType.TRIBE_PICK)) {
//                TODO ADD TEAM MODEL, SERVICE, CONTROLLER, AND REPO
//                question.setPotentialAnswers(teamService.getAvailableTeams());
            }

            if (questionFromRequest.getEpisodeId() != null) {
                question.setEpisode(episodeService.getEpisodeById(questionFromRequest.getEpisodeId()));
            }

            return question;
        }).collect(Collectors.toList());

        return questionRepo.saveAll(questions);
    }

    // Update a question details
    public void updateQuestionDetails(UpdateQuestionRequest updateQuestionRequest) {
        Question foundQuestion = getQuestionById(updateQuestionRequest.getQuestionId());

        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isAfter(foundQuestion.getEndTime())) {
            throw new CannotUpdateEntityException(String.format("Question with id '%s' cannot be edited.  This is because the endTime of the question has passed (%t).", foundQuestion.getQuestionId(), foundQuestion.getEndTime()));
        }
        updateIfNotNull(updateQuestionRequest.getQuestionText(), foundQuestion::setQuestionText);
        updateIfNotNull(updateQuestionRequest.getPoints(), foundQuestion::setPoints);
        updateIfNotNull(updateQuestionRequest.getQuestionType(), foundQuestion::setQuestionType);
        updateIfNotNull(updateQuestionRequest.getPotentialAnswers(), foundQuestion::setPotentialAnswers);
        updateIfNotNull(updateQuestionRequest.getStartTime(), foundQuestion::setStartTime);
        updateIfNotNull(updateQuestionRequest.getEndTime(), foundQuestion::setEndTime);

        foundQuestion.setTimeUpdated(currentTime);

        setQuestionStatus(foundQuestion);
        questionRepo.save(foundQuestion);
    }

    public void deleteQuestion(Long questionId) {
        Question foundQuestion = getQuestionById(questionId);
        questionRepo.deleteById(questionId);
    }

    private Question setQuestionStatus(Question question) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isAfter(question.getStartTime()) && currentTime.isBefore(question.getEndTime())) {
            question.setStatus(QuestionStatus.OPEN);
        } else if (currentTime.isAfter(question.getEndTime()) && question.getCorrectAnswer() == null) {
            question.setStatus(QuestionStatus.PENDING);
        } else if (currentTime.isAfter(question.getEndTime()) && question.getCorrectAnswer() != null) {
            question.setStatus(QuestionStatus.RESOLVED);
        } else {
            question.setStatus(QuestionStatus.INACTIVE);
        }
        return question;
    }

    private boolean doesQuestionStatusNeedUpdating(Question question) {
        LocalDateTime currentTime = LocalDateTime.now();

        if (question.getStatus().equals(QuestionStatus.INACTIVE) && question.getStartTime().isBefore(LocalDateTime.now())) {
            return true;
        }
        if (question.getStatus().equals(QuestionStatus.OPEN) && question.getEndTime().isBefore(currentTime)) {
            return true;
        }
        return false;
    }

    public void resolveQuestion(Question question) {
        question.setStatus(QuestionStatus.RESOLVED);
        question.setTimeUpdated(LocalDateTime.now());
        questionRepo.save(question);
    }

    @Transactional
    public void validateQuestionStatus() throws Exception {
        List<Question> questionsToCheck = new ArrayList<>();
        questionsToCheck.addAll(questionRepo.findByStatus(QuestionStatus.OPEN));
        questionsToCheck.addAll(questionRepo.findByStatus(QuestionStatus.OPEN));
        questionsToCheck.addAll(questionRepo.findByStatus(QuestionStatus.OPEN));

        List<Question> questionsToBeSaved = questionsToCheck.stream()
                .filter(question -> doesQuestionStatusNeedUpdating(question))
                .map(question -> setQuestionStatus(question))
                .collect(Collectors.toList());
        questionRepo.saveAll(questionsToBeSaved);
    }
}
