package com.kleintwins.ftr.show.service;

import com.kleintwins.ftr.show.code.ContestantStatus;
import com.kleintwins.ftr.show.code.Show;
import com.kleintwins.ftr.show.model.ContestantModel;
import com.kleintwins.ftr.show.model.EpisodeId;
import com.kleintwins.ftr.show.model.SeasonId;
import com.kleintwins.ftr.show.model.SeasonModel;
import com.kleintwins.ftr.show.repository.ContestantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ContestantService {
    private final ContestantRepository contestantRepo;

    private final SeasonService seasonService;

    public List<ContestantModel> getAllContestants() {
        return contestantRepo.findAll();
    }

    public List<ContestantModel> getContestantsForSeason(Show show, int seasonSequence) {
        SeasonModel seasonModel = seasonService.findSeasonByShowAndSeasonId(show, seasonSequence);
        return seasonModel.getContestants();
    }

    public List<ContestantModel> getContestantsForShow(Show show) {
        return contestantRepo.findAllBySeasonsSeasonIdShow(show);
    }

    public List<ContestantModel> getContestantsForEpisodeByStatus(
            ContestantStatus contestantStatus, Show show, Integer seasonSequence, Integer episodeSequence) {

        SeasonId seasonId = new SeasonId(show, seasonSequence);
        EpisodeId episodeId = new EpisodeId(episodeSequence, seasonId);

        return contestantRepo.findAllBySeasonsSeasonIdEpisodeIdAndStatusesStatus(episodeId, contestantStatus);
    }

    public List<ContestantModel> getContestantsForEpisodeByStatus(ContestantStatus contestantStatus, EpisodeId episodeId) {
        Show show = episodeId.getSeasonId().getShow();
        Integer seasonSequence = episodeId.getSeasonId().getSeasonSequence();
        Integer episodeSequence = episodeId.getEpisodeSequence();
        return getContestantsForEpisodeByStatus(contestantStatus, show, seasonSequence, episodeSequence)
    }

    public List<ContestantModel> getAliveContestantsByBeginningOfEpisode(ShEpisodeId episodeId) {
        return getContestantsForEpisodeByStatus(episodeId);
    }

}
