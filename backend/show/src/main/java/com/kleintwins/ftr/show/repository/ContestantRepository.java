package com.kleintwins.ftr.show.repository;

import com.kleintwins.ftr.show.code.ContestantStatus;
import com.kleintwins.ftr.show.code.Show;
import com.kleintwins.ftr.show.model.ContestantModel;
import com.kleintwins.ftr.show.model.EpisodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestantRepository extends JpaRepository<ContestantModel, String > {
    List<ContestantModel> findAllBySeasonsSeasonIdShow(Show show);

    List<ContestantModel> findAllBySeasonsSeasonIdEpisodeIdAndStatusesStatus(EpisodeId episodeId, ContestantStatus status);
}
