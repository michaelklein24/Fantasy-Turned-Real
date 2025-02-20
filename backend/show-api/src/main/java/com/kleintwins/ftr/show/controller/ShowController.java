package com.kleintwins.ftr.show.controller;

import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import com.kleintwins.ftr.show.code.Show;
import com.kleintwins.ftr.show.dto.GetContestantsResponse;
import com.kleintwins.ftr.show.dto.GetSeasonsResponse;
import com.kleintwins.ftr.show.dto.GetShowsResponse;
import com.kleintwins.ftr.show.model.SeasonModel;
import com.kleintwins.ftr.show.service.SeasonService;
import com.kleintwins.ftr.show.util.SeasonResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/show")
public class ShowController {

    private final SeasonService seasonService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create a new league and assign the owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contestants successfully fetched",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetContestantsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request: Invalid Input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public GetShowsResponse getShows() {
        GetShowsResponse response = new GetShowsResponse();
        response.setShows(List.of(Show.values()));
        return response;
    }

    @GetMapping("/{show}/season")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create a new league and assign the owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seasons successfully fetched",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetSeasonsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request: Invalid Input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public ResponseEntity<GetSeasonsResponse> getSeasons(
            @PathVariable("show") Show show) {
        List<SeasonModel> seasonModels = seasonService.getSeasonsForShow(show);
        return ResponseEntity.ok().body(SeasonResponseBuilder.buildGetSeasonsResponse(seasonModels));
    }

}
