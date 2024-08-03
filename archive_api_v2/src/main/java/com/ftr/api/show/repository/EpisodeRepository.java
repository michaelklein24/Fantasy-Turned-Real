package com.ftr.api.show.repository;

import com.ftr.api.show.model.EpisodeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<EpisodeModel, Integer> {

}
