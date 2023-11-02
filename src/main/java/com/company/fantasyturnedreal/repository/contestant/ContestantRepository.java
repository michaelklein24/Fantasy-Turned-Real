package com.company.fantasyturnedreal.repository.contestant;

import com.company.fantasyturnedreal.enums.Show;
import com.company.fantasyturnedreal.model.contestant.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestantRepository extends JpaRepository<Contestant, Long> {
    List<Contestant> findBySeasonsShow(Show show);
    List<Contestant> findBySeasonsSeasonId(Long seasonId);
    List<Contestant> findBySeasonsShowAndSeasonsSeasonId(Show show, Long seasonId);
}
