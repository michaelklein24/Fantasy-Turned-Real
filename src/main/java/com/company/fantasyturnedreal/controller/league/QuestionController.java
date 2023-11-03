package com.company.fantasyturnedreal.controller.league;

import com.company.fantasyturnedreal.dto.league.CreateQuestionRequest;
import com.company.fantasyturnedreal.dto.league.UpdateQuestionRequest;
import com.company.fantasyturnedreal.dto.league.UpdateQuestionWithCorrectAnswerRequest;
import com.company.fantasyturnedreal.enums.QuestionStatus;
import com.company.fantasyturnedreal.exception.MismatchingIdsException;
import com.company.fantasyturnedreal.model.league.Question;
import com.company.fantasyturnedreal.service.league.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Question> getQuestions(
            @RequestParam(name = "leagueId", required = true) Long leagueId,
            @RequestParam(name = "questionStatus", required = false) QuestionStatus questionStatus,
            @RequestParam(name = "episodeId", required = false) Long episodeId) {
        return questionService.getQuestions(leagueId, episodeId, questionStatus);
    }

    @GetMapping("/{questionId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Question getQuestionById(@PathVariable Long questionId) {
        return questionService.getQuestionById(questionId);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<Question> createQuestion(@Valid @RequestBody List<CreateQuestionRequest> request) {
        return questionService.createQuestions(request);
    }

    @PutMapping("/{questionId}/answer")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateQuestionWithCorrectAnswer(@PathVariable Long questionId, @Valid @RequestBody UpdateQuestionWithCorrectAnswerRequest request) {
        if (!request.getQuestionId().equals(questionId)) {
            throw new MismatchingIdsException(String.format("The question id found in the request body (%s) does not match the question id found in the path (%d).", request.getQuestionId(), questionId));
        }
        questionService.updateQuestionWithCorrectAnswer(request.getQuestionId(), request.getCorrectAnswer());
    }

    @PutMapping("/{questionId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateQuestionDetails(@PathVariable Long questionId, @Valid @RequestBody UpdateQuestionRequest request) {
        if (!request.getQuestionId().equals(questionId)) {
            throw new MismatchingIdsException(String.format("The question id found in the request body (%s) does not match the question id found in the path (%d).", request.getQuestionId(), questionId));
        }
        questionService.updateQuestionDetails(request);
    }

    @DeleteMapping("/{questionId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
    }
}
