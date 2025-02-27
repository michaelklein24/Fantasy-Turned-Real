package com.kleintwins.ftr.show.repository;

import com.kleintwins.ftr.show.code.ContestantStatus;
import com.kleintwins.ftr.show.model.ContestantModel;
import com.kleintwins.ftr.show.model.EpisodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestantRepository extends JpaRepository<ContestantModel, String> {
    List<ContestantModel> findAllBySeasonsSeasonIdShow(String show);

    List<ContestantModel> findAllBySeasonsEpisodesEpisodeIdAndStatusesStatus(EpisodeId episodeId, ContestantStatus status);
}
