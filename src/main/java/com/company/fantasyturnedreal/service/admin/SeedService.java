package com.company.fantasyturnedreal.service.admin;

import com.company.fantasyturnedreal.dto.contestant.CreateContestantRequest;
import com.company.fantasyturnedreal.dto.contestant.CreateContestantStatusRequest;
import com.company.fantasyturnedreal.dto.league.CreateAnswerRequest;
import com.company.fantasyturnedreal.dto.league.CreateLeagueRequest;
import com.company.fantasyturnedreal.dto.league.CreateQuestionRequest;
import com.company.fantasyturnedreal.dto.season.CreateEpisodeRequest;
import com.company.fantasyturnedreal.dto.season.CreateSeasonRequest;
import com.company.fantasyturnedreal.dto.user.RegisterUserRequest;
import com.company.fantasyturnedreal.enums.QuestionType;
import com.company.fantasyturnedreal.enums.Show;
import com.company.fantasyturnedreal.enums.Status;
import com.company.fantasyturnedreal.service.AbstractService;
import com.company.fantasyturnedreal.service.contestant.ContestantService;
import com.company.fantasyturnedreal.service.contestant.ContestantStatusService;
import com.company.fantasyturnedreal.service.league.AnswerService;
import com.company.fantasyturnedreal.service.league.LeagueService;
import com.company.fantasyturnedreal.service.league.QuestionService;
import com.company.fantasyturnedreal.service.league.ScoreService;
import com.company.fantasyturnedreal.service.season.EpisodeService;
import com.company.fantasyturnedreal.service.season.SeasonService;
import com.company.fantasyturnedreal.service.user.PasswordService;
import com.company.fantasyturnedreal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class SeedService extends AbstractService {

    private final ContestantService contestantService;
    private final ContestantStatusService contestantStatusService;
    private final AnswerService answerService;
    private final LeagueService leagueService;
    private final QuestionService questionService;
    private final ScoreService scoreService;
    private final EpisodeService episodeService;
    private final SeasonService seasonService;
    private final PasswordService passwordService;
    private final UserService userService;

    @Autowired
    public SeedService(
            ContestantService contestantService,
            ContestantStatusService contestantStatusService,
            AnswerService answerService,
            LeagueService leagueService,
            QuestionService questionService,
            ScoreService scoreService,
            EpisodeService episodeService,
            SeasonService seasonService,
            PasswordService passwordService,
            UserService userService) {
        this.contestantService = contestantService;
        this.contestantStatusService = contestantStatusService;
        this.answerService = answerService;
        this.leagueService = leagueService;
        this.questionService = questionService;
        this.scoreService = scoreService;
        this.episodeService = episodeService;
        this.seasonService = seasonService;
        this.passwordService = passwordService;
        this.userService = userService;
    }

    public void seedDatabase() throws Exception {
        createUsers();
        createSeasons();
        createEpisodes();
        createLeagues();
        createQuestions();
        createAnswers();
//        updateCorrectAnswers();
        createContestants();
        createContestantStatus();
    }

    private void createUsers() {
        userService.registerNewUser(RegisterUserRequest.builder()
                .username("user1").email("user1@gmail.com").password("password")
                .build());
        userService.registerNewUser(RegisterUserRequest.builder()
                .username("user2").email("user2@gmail.com").password("password")
                .build());
    }

    private void createSeasons() {
        seasonService.createSeason(CreateSeasonRequest.builder()
                        .title("Survivor: Island of the Idols")
                        .show(Show.SURVIVOR)
                        .seasonNumber(39L)
                .build());
        seasonService.createSeason(CreateSeasonRequest.builder()
                .title("Survivor: Winners at War")
                .show(Show.SURVIVOR)
                .seasonNumber(40L)
                .build());
    }

    private void createEpisodes() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        episodeService.createEpisode(CreateEpisodeRequest.builder()
                .airDate(dateFormat.parse("2023-01-10"))
                .title("Episode 1")
                .seasonId(1L)
                .build());
        episodeService.createEpisode(CreateEpisodeRequest.builder()
                .airDate(dateFormat.parse("2023-01-17"))
                .title("Episode 2")
                .seasonId(1L)
                .build());
        episodeService.createEpisode(CreateEpisodeRequest.builder()
                .airDate(dateFormat.parse("2024-01-10"))
                .title("Episode 1")
                .seasonId(2L)
                .build());
        episodeService.createEpisode(CreateEpisodeRequest.builder()
                .airDate(dateFormat.parse("2024-01-17"))
                .title("Episode 2")
                .seasonId(2L)
                .build());
    }

    private void createLeagues() {
        leagueService.createLeague(CreateLeagueRequest.builder()
                        .adminUserId(1L)
                        .seasonId(1L)
                        .leagueName("Fantasy Survivor League 1")
                .build());
        leagueService.createLeague(CreateLeagueRequest.builder()
                        .adminUserId(2L)
                        .seasonId(1L)
                        .leagueName("Fantasy Survivor League 2")
                .build());
    }

    private void createQuestions() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<CreateQuestionRequest> questionsToBeCreated1 = Arrays.asList(
                CreateQuestionRequest.builder()
                        .points(10)
                        .questionType(QuestionType.MULTIPLE_CHOICE)
                        .questionText("Who will be voted out in Episode 1?")
                        .startTime(LocalDateTime.parse("2023-01-10 19:00:00", formatter))
                        .endTime(LocalDateTime.parse("2023-01-10 20:00:00", formatter))
                        .potentialAnswers(Arrays.asList(
                                "Contestant A",
                                "Contestant B",
                                "Contestant C",
                                "Contestant D"
                        ))
                        .episodeId(1L)
                        .leagueId(1L)
                        .build(),
                CreateQuestionRequest.builder()
                        .points(10)
                        .questionType(QuestionType.MULTIPLE_CHOICE)
                        .questionText("Who will find the hidden immunity idol in Episode 1?")
                        .startTime(LocalDateTime.parse("2023-01-10 19:00:00", formatter))
                        .endTime(LocalDateTime.parse("2023-01-10 20:00:00", formatter))
                        .potentialAnswers(Arrays.asList(
                                "Contestant A",
                                "Contestant B",
                                "Contestant C",
                                "Contestant D"
                        ))
                        .episodeId(1L)
                        .leagueId(1L)
                        .build(),
                CreateQuestionRequest.builder()
                        .points(10)
                        .questionType(QuestionType.MULTIPLE_CHOICE)
                        .questionText("Who will win the reward challenge in Episode 1?")
                        .startTime(LocalDateTime.parse("2023-01-10 19:00:00", formatter))
                        .endTime(LocalDateTime.parse("2023-01-10 20:00:00", formatter))
                        .potentialAnswers(Arrays.asList(
                                "Contestant A",
                                "Contestant B",
                                "Contestant C",
                                "Contestant D"
                        ))
                        .episodeId(1L)
                        .leagueId(1L)
                        .build(),
                CreateQuestionRequest.builder()
                        .points(10)
                        .questionType(QuestionType.MULTIPLE_CHOICE)
                        .questionText("Who will be voted out in Episode 1?")
                        .startTime(LocalDateTime.parse("2023-01-10 19:00:00", formatter))
                        .endTime(LocalDateTime.parse("2023-01-10 20:00:00", formatter))
                        .potentialAnswers(Arrays.asList(
                                "Contestant A",
                                "Contestant B",
                                "Contestant C",
                                "Contestant D"
                        ))
                        .episodeId(1L)
                        .leagueId(1L)
                        .build()
        );
        questionService.createQuestions(questionsToBeCreated1);
    }

    private void createAnswers() {
        answerService.createAnswer(CreateAnswerRequest.builder()
                        .answer("Contestant A")
                        .userId(1L)
                .build());
        answerService.createAnswer(CreateAnswerRequest.builder()
                        .answer("Contestant B")
                        .userId(2L)
                .build());
        answerService.createAnswer(CreateAnswerRequest.builder()
                .answer("Contestant B")
                .userId(1L)
                .build());
        answerService.createAnswer(CreateAnswerRequest.builder()
                .answer("Contestant B")
                .userId(2L)
                .build());
    }

//    private void updateCorrectAnswers() {
//        questionService.updateQuestionWithCorrectAnswer(1L, "Contestant A");
//        questionService.updateQuestionWithCorrectAnswer(2L, "Contestant B");
//    }

    private void createContestants() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        contestantService.createContestant(CreateContestantRequest.builder()
                        .firstName("Contestant A")
                        .lastName("Lastname A")
                        .occupation("Occupation A")
                        .originCity("City A")
                        .originState("City B")
                        .birthday(dateFormat.parse("1990-01-01"))
                        .nickname("Nickname A")
                        .biography("Biography A")
                .build());

        contestantService.createContestant(CreateContestantRequest.builder()
                .firstName("Contestant B")
                .lastName("Lastname B")
                .occupation("Occupation B")
                .originCity("City B")
                .originState("City B")
                .birthday(dateFormat.parse("1992-02-02"))
                .nickname("Nickname A")
                .biography("Biography A")
                .build());
    }

    private void createSocialAccounts() {
    }

    private void createContestantStatus() {
        contestantStatusService.createContestantStatus(CreateContestantStatusRequest.builder()
                        .contestantId(1L)
                        .status(Status.ALIVE)
                        .episodeId(1L)
                        .seasonId(1L)
                .build());
        contestantStatusService.createContestantStatus(CreateContestantStatusRequest.builder()
                .contestantId(2L)
                .status(Status.ALIVE)
                .episodeId(1L)
                .seasonId(1L)
                .build());
    }

}