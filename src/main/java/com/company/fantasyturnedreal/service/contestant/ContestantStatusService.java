package com.company.fantasyturnedreal.service.contestant;

import com.company.fantasyturnedreal.dto.contestant.CreateContestantStatusRequest;
import com.company.fantasyturnedreal.dto.contestant.UpdateContestantStatusRequest;
import com.company.fantasyturnedreal.enums.Status;
import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.model.contestant.ContestantStatus;
import com.company.fantasyturnedreal.repository.contestant.ContestantStatusRepository;
import com.company.fantasyturnedreal.service.season.EpisodeService;
import com.company.fantasyturnedreal.service.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestantStatusService {
    @Autowired
    ContestantStatusRepository contestantStatusRepo;

    @Autowired
    SeasonService seasonService;

    @Autowired
    ContestantService contestantService;

    @Autowired
    EpisodeService episodeService;

    public List<ContestantStatus> getContestantStatuses(Status status, Long seasonId, Long episodeId, Long contestantId) {
//        if (status == null && seasonId == null && episodeId == null && contestantId == null) {
//            return contestantStatusRepo.findAll();
//        }
//
//        if (status != null && seasonId != null && episodeId != null && contestantId != null) {
//            return contestantStatusRepo.findByStatusAndSeasonSeasonIdAndEpisodeEpisodeIdAndContestantContestantId(status, seasonId, episodeId, contestantId);
//        }

        Specification<ContestantStatus> spec = Specification.where(null);

        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }

        if (seasonId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("season").get("seasonId"), seasonId));
        }

        if (episodeId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("episode").get("episodeId"), episodeId));
        }

        if (contestantId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("contestant").get("contestantId"), contestantId));
        }

        return contestantStatusRepo.findAll(spec);
    }

    public ContestantStatus getContestantStatusById(Long contestantStatusId) {
        ContestantStatus foundContestantStatus = contestantStatusRepo.findById(contestantStatusId).orElse(null);
        if (foundContestantStatus == null) {
            throw new DataNotFoundException(String.format("ContestantStatus with id '%s' does not exist.", contestantStatusId));
        }

        return foundContestantStatus;
    }

    public ContestantStatus createContestantStatus(CreateContestantStatusRequest createContestantStatusRequest) {
        ContestantStatus contestantStatus = new ContestantStatus();
        contestantStatus.setStatus(createContestantStatusRequest.getStatus());
        contestantStatus.setContestant(contestantService.getContestantById(createContestantStatusRequest.getContestantId()));
        contestantStatus.setEpisode(episodeService.getEpisodeById(createContestantStatusRequest.getEpisodeId()));
        contestantStatus.setSeason(seasonService.getSeasonById(createContestantStatusRequest.getSeasonId()));

        return contestantStatusRepo.save(contestantStatus);
    }

    public void updateContestantStatus(UpdateContestantStatusRequest updateContestantStatusRequest) {
        ContestantStatus foundContestantStatus = getContestantStatusById(updateContestantStatusRequest.getContestantStatusId());
        if (updateContestantStatusRequest.getStatus() != null) {
            foundContestantStatus.setStatus(updateContestantStatusRequest.getStatus());
        }
        contestantStatusRepo.save(foundContestantStatus);
    }

    public void deleteContestantStatus(Long contestantStatusId) {
        ContestantStatus foundContestantStatus = getContestantStatusById(contestantStatusId);
        contestantStatusRepo.deleteById(contestantStatusId);
    }
}
