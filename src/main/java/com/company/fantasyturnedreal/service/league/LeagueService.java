package com.company.fantasyturnedreal.service.league;

import com.company.fantasyturnedreal.dto.league.CreateLeagueRequest;
import com.company.fantasyturnedreal.dto.league.UpdateLeagueRequest;
import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.exception.MismatchingIdsException;
import com.company.fantasyturnedreal.model.league.League;
import com.company.fantasyturnedreal.model.season.Season;
import com.company.fantasyturnedreal.model.user.User;
import com.company.fantasyturnedreal.repository.league.LeagueRepository;
import com.company.fantasyturnedreal.service.season.SeasonService;
import com.company.fantasyturnedreal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LeagueService {

    @Autowired
    UserService userService;

    @Autowired
    SeasonService seasonService;

    @Autowired
    LeagueRepository leagueRepo;

    public League createLeague(CreateLeagueRequest createLeagueRequest) {
        League league = new League();
        User user = userService.getUserById(createLeagueRequest.getAdminUserId());
        league.getUsers().add(user);

        Season season = seasonService.getSeasonById(createLeagueRequest.getSeasonId());
        league.setSeason(season);

        return leagueRepo.save(league);
    }

    public void updateLeague(UpdateLeagueRequest updateLeagueRequest) {
        League league = getLeagueById(updateLeagueRequest.getLeagueId());
        if (!updateLeagueRequest.getLeagueName().isBlank()) {
            league.setLeagueName(updateLeagueRequest.getLeagueName());
        }
        leagueRepo.save(league);
    }

    public void deleteLeague(Long leagueId) {
        League league = getLeagueById(leagueId);
        leagueRepo.deleteById(leagueId);
    }

    public League getLeagueById(Long leagueId) throws MismatchingIdsException {
        League league = leagueRepo.findById(leagueId).orElse(null);
        if (league == null) {
            throw new DataNotFoundException(String.format("League with id '%s' does not exist.", leagueId));
        }
        return league;
    }

    public List<League> getAllLeagues() {
        return leagueRepo.findAll();
    }

    public League addUserToLeague(Long userId, Long leagueId) {
        League league = getLeagueById(leagueId);
        Set<User> currentLeagueUsers = league.getUsers();
        User user = userService.getUserById(userId);
        currentLeagueUsers.add(user);

        return leagueRepo.save(league);
    }

    public void removeUserFromLeague(Long userId, Long leagueId) {
        League league = getLeagueById(leagueId);
        Set<User> currentLeagueUsers = league.getUsers();

        currentLeagueUsers.removeIf(user -> user.getUserId().equals(userId));

        leagueRepo.save(league);
    }

    public Set<User> getUsersInLeague(Long leagueId) {
        League league = getLeagueById(leagueId);
        return league.getUsers();
    }
}
