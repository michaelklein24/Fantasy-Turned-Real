package com.ftr.api.league.controller;

import com.ftr.api.core.controller.AbstractController;
import com.ftr.api.league.dto.GetPendingInvitesForUserResponse;
import com.ftr.api.league.dto.InviteParticipantsToLeagueRequest;
import com.ftr.api.league.dto.ResolveInviteRequest;
import com.ftr.api.league.service.InviteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invite")
public class InviteController extends AbstractController {

    private final InviteService inviteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetPendingInvitesForUserResponse getInvitesForUser(HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return inviteService.getInvitesForUser(userId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void inviteUsersToLeague(@RequestBody InviteParticipantsToLeagueRequest inviteParticipantsToLeagueRequest, HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        inviteService.inviteUsersToLeague(inviteParticipantsToLeagueRequest, userId);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addressInvite(@RequestBody ResolveInviteRequest resolveInviteRequest, HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        inviteService.resolveInvite(resolveInviteRequest, userId);
    }
}
