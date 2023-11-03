package com.company.fantasyturnedreal.controller.league;

import com.company.fantasyturnedreal.dto.league.AddUserToLeagueRequest;
import com.company.fantasyturnedreal.dto.league.CreateLeagueRequest;
import com.company.fantasyturnedreal.dto.league.RemoveUserFromLeagueRequest;
import com.company.fantasyturnedreal.dto.league.UpdateLeagueRequest;
import com.company.fantasyturnedreal.exception.MismatchingIdsException;
import com.company.fantasyturnedreal.model.league.League;
import com.company.fantasyturnedreal.model.user.User;
import com.company.fantasyturnedreal.service.league.LeagueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.company.fantasyturnedreal.util.RestApiSupport.REST_API_CONTEXT_PATH;

@CrossOrigin("*")
@RestController
@RequestMapping(REST_API_CONTEXT_PATH + "/league")
public class LeagueController {

    @Autowired
    LeagueService leagueService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<League> getLeagues() {
        return leagueService.getAllLeagues();
    }

    @GetMapping("/{leagueId}")
    @ResponseStatus(code = HttpStatus.OK)
    public League getLeagueById(@PathVariable Long leagueId) {
        return leagueService.getLeagueById(leagueId);
    }

    @GetMapping("/{leagueId}/user")
    @ResponseStatus(code = HttpStatus.OK)
    public Set<User> getUsersInLeague(@PathVariable Long leagueId) {
        return leagueService.getUsersInLeague(leagueId);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public League createLeague(@Valid @RequestBody CreateLeagueRequest request) {
        return leagueService.createLeague(request);
    }

    @PostMapping("/user")
    @ResponseStatus(code = HttpStatus.OK)
    public League addUserToLeague(@Valid @RequestBody AddUserToLeagueRequest request) {
        return leagueService.addUserToLeague(request.getUserId(), request.getLeagueId());
    }

    @PutMapping("/{leagueId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateLeagueDetails(@PathVariable Long leagueId, @Valid @RequestBody UpdateLeagueRequest request) {
        if (!request.getLeagueId().equals(leagueId)) {
            throw new MismatchingIdsException(String.format("The league id found in the request body (%s) does not match the league id found in the path (%d).", request.getLeagueId(), leagueId));
        }

        leagueService.updateLeague(request);
    }

    @DeleteMapping("/{leagueId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteLeague(@PathVariable Long leagueId) {
        leagueService.deleteLeague(leagueId);
    }

    @DeleteMapping("/user")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeUserFromLeague(@Valid @RequestBody RemoveUserFromLeagueRequest request) {
        leagueService.removeUserFromLeague(request.getUserId(), request.getLeagueId());
    }

}
