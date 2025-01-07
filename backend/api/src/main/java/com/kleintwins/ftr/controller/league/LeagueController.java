package com.kleintwins.ftr.controller.league;

import com.kleintwins.ftr.auth.helper.JwtHelper;
import com.kleintwins.ftr.auth.service.TokenService;
import com.kleintwins.ftr.controller.league.dto.CreateLeagueRequest;
import com.kleintwins.ftr.controller.league.dto.CreateLeagueResponse;
import com.kleintwins.ftr.controller.league.dto.League;
import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import com.kleintwins.ftr.core.model.LeagueModel;
import com.kleintwins.ftr.core.service.LeagueService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;
    private final JwtHelper jwtHelper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new league and assign the owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "League created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateLeagueResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request: Validation failed or invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
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
    public ResponseEntity<CreateLeagueResponse> createLeague(
            @RequestBody @Valid CreateLeagueRequest createLeagueRequest,
            HttpServletRequest request
    ) {
        String name = createLeagueRequest.getName();
        String userId = jwtHelper.extractUserIdFromTokenInRequest(request);
        LeagueModel createdLeague = leagueService.createLeague(name, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(LeagueResponseBuilder.buildCreateLeagueResponse(createdLeague));
    }

}
