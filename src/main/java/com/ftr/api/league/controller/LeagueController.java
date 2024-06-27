package com.ftr.api.league.controller;

import com.ftr.api.core.controller.AbstractController;
import com.ftr.api.league.dto.CreateLeagueRequest;
import com.ftr.api.league.dto.CreateLeagueResponse;
import com.ftr.api.league.dto.GetLeagueDetailsByIdForUserResponse;
import com.ftr.api.league.dto.GetLeagueSummariesForUser;
import com.ftr.api.league.service.LeagueService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/league")
public class LeagueController extends AbstractController {

    private final LeagueService leagueService;

    @GetMapping
    public GetLeagueSummariesForUser getLeaguesForUser(HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return leagueService.getLeagueSummariesForUser(userId);
    }

    @GetMapping
    @RequestMapping("/:leagueId")
    public GetLeagueDetailsByIdForUserResponse getLeagueDetailsById(@PathVariable Integer leagueId, HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return leagueService.getLeagueDetailsByIdForUser(userId, leagueId);
    }

    @PostMapping
    public CreateLeagueResponse createLeague(@RequestParam("file") MultipartFile file, @RequestBody CreateLeagueRequest createLeagueRequest, HttpServletRequest request) {
        Integer userId = extractUserIdFromToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return leagueService.createLeague(file, createLeagueRequest, userId);
    }
}
