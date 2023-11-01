package com.company.fantasyturnedreal.repository.season;

import com.company.fantasyturnedreal.enums.Show;
import com.company.fantasyturnedreal.model.season.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

    List<Season> findByShow(Show show);

}
