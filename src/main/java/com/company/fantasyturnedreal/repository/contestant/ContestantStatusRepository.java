package com.company.fantasyturnedreal.repository.contestant;

import com.company.fantasyturnedreal.enums.Status;
import com.company.fantasyturnedreal.model.contestant.ContestantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestantStatusRepository extends JpaRepository<ContestantStatus, Long>, JpaSpecificationExecutor<ContestantStatus> {
    List<ContestantStatus> findByContestantContestantId(Long contestantId);
    List<ContestantStatus> findByContestantContestantIdAndSeasonSeasonId(Long contestantId, Long seasonId);
    List<ContestantStatus> findByContestantContestantIdAndEpisodeEpisodeId(Long contestantId, Long episodeId);
    List<ContestantStatus> findByStatusAndSeasonSeasonIdAndEpisodeEpisodeId(Status status, Long seasonId, Long episodeId);
    List<ContestantStatus> findByStatusAndSeasonSeasonIdAndEpisodeEpisodeIdAndContestantContestantId(Status status, Long seasonId, Long episodeId, Long contestantId);
}
