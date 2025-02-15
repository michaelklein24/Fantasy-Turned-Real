package com.kleintwins.ftr.show.controller;

import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import com.kleintwins.ftr.show.code.ContestantStatus;
import com.kleintwins.ftr.show.code.Show;
import com.kleintwins.ftr.show.dto.ContestantDtoBuilder;
import com.kleintwins.ftr.show.dto.GetContestantsResponse;
import com.kleintwins.ftr.show.model.ContestantModel;
import com.kleintwins.ftr.show.service.ContestantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contestant")
public class ContestantController {

    private final ContestantService contestantService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create a new league and assign the owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contestants successfully fetched",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetContestantsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request: Validation failed or invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: Contestants cannot be found with the following request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public ResponseEntity<GetContestantsResponse> getContestants(
            @RequestParam(name = "show", required = false) Show show,
            @RequestParam(name = "seasonSequence", required = false) Integer seasonSequence,
            @RequestParam(name = "status", required = false) ContestantStatus contestantStatus,
            @RequestParam(name = "episodeSequence", required = false) Integer episodeSequence
            ) {
        List<ContestantModel> contestantModels;

        if (contestantStatus != null && show != null && seasonSequence != null && episodeSequence != null) {
            contestantModels = contestantService.getContestantsForEpisodeByStatus(contestantStatus, show, seasonSequence, episodeSequence);
        } else if (show != null && seasonSequence != null) {
            contestantModels = contestantService.getContestantsForSeason(show, seasonSequence);
        } else if (show != null) {
            contestantModels = contestantService.getContestantsForShow(show);
        } else {
            contestantModels = contestantService.getAllContestants();
        }

        return ResponseEntity.status(HttpStatus.OK).body(ContestantDtoBuilder.buildGetContestantsResponse(contestantModels));
    }


}
