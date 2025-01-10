package com.kleintwins.ftr.league.controller;

import com.kleintwins.ftr.auth.helper.JwtHelper;
import com.kleintwins.ftr.league.code.InviteStatus;
import com.kleintwins.ftr.league.dto.*;
import com.kleintwins.ftr.league.model.InviteModel;
import com.kleintwins.ftr.league.util.LeagueDtoBuilder;
import com.kleintwins.ftr.show.code.Show;
import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import com.kleintwins.ftr.league.model.LeagueModel;
import com.kleintwins.ftr.league.service.LeagueService;
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

import java.util.List;

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
        Show show = createLeagueRequest.getShow();
        int seasonSequence = createLeagueRequest.getSeasonSequence();
        LeagueModel createdLeague = leagueService.createLeague(name, userId, show, seasonSequence);

        return ResponseEntity.status(HttpStatus.CREATED).body(LeagueDtoBuilder.buildCreateLeagueResponse(createdLeague));
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Fetch Leagues")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of leagues for user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetLeaguesForUserResponse.class))),
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
    public ResponseEntity<GetLeaguesForUserResponse> getLeaguesForUser(
            HttpServletRequest request
    ) {
        String userId = jwtHelper.extractUserIdFromTokenInRequest(request);
        List<LeagueModel> leagueModels = leagueService.getLeaguesForUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(LeagueDtoBuilder.buildGetLeaguesForUserResponse(leagueModels));
    }

    @PostMapping("/{leagueId}/invite")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Invite user to league")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Invite successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InviteUserToLeagueResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request: Request missing object",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: Insufficient permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: Data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict: User Already Invited",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public ResponseEntity<InviteUserToLeagueResponse> inviteUserToLeague(
            @PathVariable("leagueId") String leagueId,
            @RequestBody @Valid InviteUserToLeagueRequest inviteUserToLeagueRequest,
            HttpServletRequest request
    ) {
        String inviterUserId = jwtHelper.extractUserIdFromTokenInRequest(request);
        String inviteeEmail = inviteUserToLeagueRequest.getInviteeEmail();

        InviteModel inviteModel = leagueService.createInvite(inviteeEmail, inviterUserId, leagueId);
        return ResponseEntity.status(201).body(LeagueDtoBuilder.buildCreateLeagueInviteResponse(inviteModel));
    }

    @GetMapping("/{leagueId}/invite")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Fetch invites for league")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invites successfully retrieved",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetInvitesForLeagueResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request: Missing leagueId path variable",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: Insufficient permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: League not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public ResponseEntity<GetInvitesForLeagueResponse> getInvitesForLeague(
            @PathVariable("leagueId") String leagueId
    ) {
        List<InviteModel> inviteModels = leagueService.getInvitesForLeague(leagueId);
        return ResponseEntity.status(200).body(LeagueDtoBuilder.buildGetInvitesForLeagueResponse(inviteModels));
    }

    @PutMapping("/{leagueId}/invite")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update Invite Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Invites successfully updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InviteUserToLeagueResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: Insufficient permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: Data Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public void acceptOrRejectLeagueInvite(
            @RequestBody UpdateInviteStatusRequest updateInviteStatusRequest,
            @PathVariable("leagueId") String leagueId,
            HttpServletRequest request
    ) {
        String inviteeUserId = jwtHelper.extractUserIdFromTokenInRequest(request);
        InviteStatus newInviteStatus = updateInviteStatusRequest.getNewStatus();

        leagueService.processInvite(inviteeUserId, leagueId, newInviteStatus);
    }
}
