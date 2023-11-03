package com.company.fantasyturnedreal.controller.league;

import com.company.fantasyturnedreal.dto.league.CreateAnswerRequest;
import com.company.fantasyturnedreal.dto.league.UpdateAnswerRequest;
import com.company.fantasyturnedreal.exception.MismatchingIdsException;
import com.company.fantasyturnedreal.model.league.Answer;
import com.company.fantasyturnedreal.service.league.AnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.company.fantasyturnedreal.util.RestApiSupport.REST_API_CONTEXT_PATH;

@RestController
@RequestMapping(REST_API_CONTEXT_PATH + "/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Answer> getAnswers(
            @RequestParam(name = "questionId", required = true) Long questionId,
            @RequestParam(name = "userId", required = false) Long userId) {
        return answerService.getAnswers(questionId, userId, null, null);
    }

    @GetMapping("/{answerId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Answer getAnswerById(@PathVariable Long answerId) {
        return answerService.getAnswerById(answerId);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Answer createAnswer(@Valid @RequestBody CreateAnswerRequest request) {
        return answerService.createAnswer(request);
    }

    @PutMapping("/{answerId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateAnswerDetails(@PathVariable Long answerId, @Valid @RequestBody UpdateAnswerRequest request) {
        if (!request.getAnswerId().equals(answerId)) {
            throw new MismatchingIdsException(String.format("The answer id found in the request body (%s) does not match the answer id found in the path (%d).", request.getAnswerId(), answerId));
        }
        answerService.updateAnswer(request);
    }
}
