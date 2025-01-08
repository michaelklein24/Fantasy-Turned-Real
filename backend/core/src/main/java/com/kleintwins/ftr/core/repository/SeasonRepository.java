package com.kleintwins.ftr.core.repository;

import com.kleintwins.ftr.core.model.SeasonId;
import com.kleintwins.ftr.core.model.SeasonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<SeasonModel, SeasonId> {

}
