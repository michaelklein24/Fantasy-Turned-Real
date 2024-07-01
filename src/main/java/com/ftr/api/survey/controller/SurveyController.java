package com.ftr.api.survey.controller;

import com.ftr.api.core.controller.AbstractController;
import com.ftr.api.survey.dto.CreateSurveyRequest;
import com.ftr.api.survey.dto.CreateSurveyResponse;
import com.ftr.api.survey.dto.GetSurveyDetailsByIdResponse;
import com.ftr.api.survey.service.SurveyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController extends AbstractController {

    private final SurveyService surveyService;

    @GetMapping("/{surveyId}")
    public GetSurveyDetailsByIdResponse getSurveyDetailsBySurveyIdForUser(@PathVariable Integer surveyId, HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return surveyService.getSurveyDetailsBySurveyIdForUser(surveyId, userId);
    }

    @PostMapping
    public CreateSurveyResponse createSurvey(@RequestBody CreateSurveyRequest createSurveyRequest, HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return surveyService.createSurvey(createSurveyRequest, userId);
    }

}
