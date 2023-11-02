package com.company.fantasyturnedreal.service.contestant;

import com.company.fantasyturnedreal.dto.contestant.CreateContestantRequest;
import com.company.fantasyturnedreal.dto.contestant.UpdateContestantRequest;
import com.company.fantasyturnedreal.enums.Show;
import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.model.contestant.Contestant;
import com.company.fantasyturnedreal.model.season.Season;
import com.company.fantasyturnedreal.repository.contestant.ContestantRepository;
import com.company.fantasyturnedreal.service.season.SeasonService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContestantService {

    @Autowired
    ContestantRepository contestantRepo;

    @Autowired
    SeasonService seasonService;

    public List<Contestant> getAllContestants() {
        return contestantRepo.findAll();
    }

    public List<Contestant> getContestants(Show show, Long seasonId) {
        if (show != null && seasonId != null) {
            return contestantRepo.findBySeasonsShowAndSeasonsSeasonId(show, seasonId);
        }
        if (show != null) {
            return contestantRepo.findBySeasonsShow(show);
        }
        if (seasonId != null) {
            return contestantRepo.findBySeasonsSeasonId(seasonId);
        }
        throw new NullPointerException("All of the method parameters are null.");
    }


    public Contestant getContestantById(Long contestantId) {
        Contestant foundContestant = contestantRepo.findById(contestantId).orElse(null);
        if (foundContestant == null) {
            throw new DataNotFoundException(String.format("Contestant with id '%s' does not exist.", contestantId));
        }
        return foundContestant;
    }

    public Contestant createContestant(CreateContestantRequest createContestantRequest) {
        Contestant contestant = new Contestant();
        contestant.setFirstName(createContestantRequest.getFirstName());
        contestant.setLastName(createContestantRequest.getLastName());
        contestant.setOccupation(createContestantRequest.getOccupation());
        contestant.setOriginCity(createContestantRequest.getOriginCity());
        contestant.setOriginState(createContestantRequest.getOriginState());
        contestant.setBirthday(createContestantRequest.getBirthday());
        contestant.setNickName(createContestantRequest.getFirstName());
        contestant.setBiography(createContestantRequest.getBiography());

        Set<Season> seasons = createContestantRequest.getSeasons().stream()
                .map(seasonId -> seasonService.getSeasonById(seasonId))
                .collect(Collectors.toSet());
        contestant.setSeasons(seasons);

        return contestantRepo.save(contestant);
    }

    public void updateContestant(UpdateContestantRequest updateContestantRequest) {
        Contestant foundContestant = getContestantById(updateContestantRequest.getContestantId());
        if (updateContestantRequest.getFirstName() != null) {
            foundContestant.setFirstName(updateContestantRequest.getFirstName());
        }
        if (updateContestantRequest.getLastName() != null) {
            foundContestant.setLastName(updateContestantRequest.getLastName());
        }
        if (updateContestantRequest.getOccupation() != null) {
            foundContestant.setOccupation(updateContestantRequest.getOccupation());
        }
        if (updateContestantRequest.getOriginCity() != null) {
            foundContestant.setOriginCity(updateContestantRequest.getOriginCity());
        }
        if (updateContestantRequest.getOriginState() != null) {
            foundContestant.setOriginState(updateContestantRequest.getOriginState());
        }
        if (updateContestantRequest.getBirthday() != null) {
            foundContestant.setBirthday(updateContestantRequest.getBirthday());
        }
        if (updateContestantRequest.getNickname() != null) {
            foundContestant.setNickName(updateContestantRequest.getNickname());
        }
        if (updateContestantRequest.getBiography() != null) {
            foundContestant.setBiography(updateContestantRequest.getBiography());
        }
        contestantRepo.save(foundContestant);
    }

    public void deleteContestant(Long contestantId) {
        Contestant foundContestant = getContestantById(contestantId);
        contestantRepo.deleteById(contestantId);
    }

}
