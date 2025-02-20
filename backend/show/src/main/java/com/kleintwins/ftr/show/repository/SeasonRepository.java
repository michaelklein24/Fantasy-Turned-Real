package com.kleintwins.ftr.show.repository;

import com.kleintwins.ftr.show.code.Show;
import com.kleintwins.ftr.show.model.SeasonId;
import com.kleintwins.ftr.show.model.SeasonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<SeasonModel, SeasonId> {
    List<SeasonModel> findBySeasonIdShow(Show show);
}
