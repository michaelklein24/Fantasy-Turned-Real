package com.ftr.api.league.controller;

import com.ftr.api.core.controller.AbstractController;
import com.ftr.api.league.dto.*;
import com.ftr.api.league.service.InviteService;
import com.ftr.api.league.service.LeagueService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/league")
public class LeagueController extends AbstractController {

    private final LeagueService leagueService;
    private final InviteService inviteService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public GetLeagueSummariesForUser getLeaguesForUser(HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return leagueService.getLeagueSummariesForUser(userId);
    }

    @GetMapping("/{leagueId}")
    @ResponseStatus(value = HttpStatus.OK)
    public GetLeagueDetailsByIdForUserResponse getLeagueDetailsById(@PathVariable Integer leagueId, HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return leagueService.getLeagueDetailsByIdForUser(userId, leagueId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CreateLeagueResponse createLeague(@RequestParam("file") MultipartFile file, @RequestBody CreateLeagueRequest createLeagueRequest, HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return leagueService.createLeague(file, createLeagueRequest, userId);
    }

    @DeleteMapping("/{leagueId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLeague(@PathVariable Integer leagueId, HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        leagueService.deleteLeague(leagueId, userId);
    }
}
