package com.company.fantasyturnedreal.service.contestant;

import com.company.fantasyturnedreal.dto.contestant.CreateContestantRequest;
import com.company.fantasyturnedreal.dto.contestant.UpdateContestantRequest;
import com.company.fantasyturnedreal.enums.Show;
import com.company.fantasyturnedreal.enums.Status;
import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.model.contestant.Contestant;
import com.company.fantasyturnedreal.model.contestant.ContestantStatus;
import com.company.fantasyturnedreal.model.season.Season;
import com.company.fantasyturnedreal.repository.contestant.ContestantRepository;
import com.company.fantasyturnedreal.service.AbstractService;
import com.company.fantasyturnedreal.service.season.SeasonService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContestantService extends AbstractService {

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

        updateIfNotNull(updateContestantRequest.getFirstName(), foundContestant::setFirstName);
        updateIfNotNull(updateContestantRequest.getLastName(), foundContestant::setLastName);
        updateIfNotNull(updateContestantRequest.getOccupation(), foundContestant::setOccupation);
        updateIfNotNull(updateContestantRequest.getOriginCity(), foundContestant::setOriginCity);
        updateIfNotNull(updateContestantRequest.getOriginState(), foundContestant::setOriginState);
        updateIfNotNull(updateContestantRequest.getBirthday(), foundContestant::setBirthday);
        updateIfNotNull(updateContestantRequest.getNickname(), foundContestant::setNickName);
        updateIfNotNull(updateContestantRequest.getBiography(), foundContestant::setBiography);

        contestantRepo.save(foundContestant);
    }

    public void deleteContestant(Long contestantId) {
        Contestant foundContestant = getContestantById(contestantId);
        contestantRepo.deleteById(contestantId);
    }

    public List<Contestant> getContestantsByStatus(Status status) {
        return contestantRepo.findByStatusesStatus(status);
    }

    public List<String> getContestantNamesByStatus(Status status) {
        List<Contestant> contestants = getContestantsByStatus(status);
        return contestants.stream()
                .map(contestant -> contestant.getFirstName() + contestant.getNickName() + contestant.getLastName())
                .collect(Collectors.toList());
    }

}
