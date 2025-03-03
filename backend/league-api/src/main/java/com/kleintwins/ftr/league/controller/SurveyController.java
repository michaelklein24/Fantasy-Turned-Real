package com.kleintwins.ftr.league.controller;

import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import com.kleintwins.ftr.league.code.SurveyType;
import com.kleintwins.ftr.league.dto.*;
import com.kleintwins.ftr.league.model.LeagueModel;
import com.kleintwins.ftr.league.model.SurveyModel;
import com.kleintwins.ftr.league.service.LeagueService;
import com.kleintwins.ftr.league.service.SurveyService;
import com.kleintwins.ftr.league.util.SurveyDtoBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;
    private final LeagueService leagueService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get surveys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of surveys",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetSurveysResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: Insufficient permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public GetSurveysResponse getSurveys(
            @RequestParam(name = "leagueId", required = false) String leagueId,
            HttpServletRequest request) {
        List<SurveyModel> surveyModels = new ArrayList<>();
        if (leagueId != null) {
            surveyModels.addAll(surveyService.getSurveysForLeague(leagueId));
        }
        return SurveyDtoBuilder.buildGetSurveysResponse(surveyModels);
    }

    @GetMapping("/{surveyId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get survey by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of survey",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetSurveyByIdResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: Insufficient permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public GetSurveyByIdResponse getSurveyById(@PathVariable(name = "surveyId") String surveyId) {
        SurveyModel surveyModel = surveyService.getSurveyById(surveyId);
        return SurveyDtoBuilder.buildGetSurveyByIdResponse(surveyModel);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Survey For League")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created survey",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateSurveyResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: Insufficient permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public CreateSurveyResponse createSurvey(@RequestBody CreateSurveyRequest request) {
        LeagueModel leagueModel = leagueService.getLeagueById(request.getLeagueId());
        SurveyModel surveyModel = surveyService.createSurvey(
                leagueModel,
                request.getName(),
                SurveyType.SPECIAL,
                request.getStartDate(),
                request.getEndDate()
        );
        return SurveyDtoBuilder.buildCreateSurveyResponse(surveyModel);
    }

    @PutMapping("/{surveyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update Survey For League")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated Survey"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: Insufficient permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public void updateSurvey(@PathVariable("surveyId") String surveyId, @Valid @RequestBody UpdateSurveyRequest request) {
        surveyService.updateSurvey(surveyId, request.getName(), request.getStartDate(), request.getEndDate());
    }

    @DeleteMapping("/{surveyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Survey by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted survey"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: Insufficient permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public void deleteSurvey(@PathVariable("surveyId") String surveyId) {
        surveyService.deleteSurvey(surveyId);
    }
}
