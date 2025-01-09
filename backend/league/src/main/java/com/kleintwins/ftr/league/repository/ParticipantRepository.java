package com.kleintwins.ftr.league.repository;

import com.kleintwins.ftr.league.model.ParticipantId;
import com.kleintwins.ftr.league.model.ParticipantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantModel, ParticipantId> {
    List<ParticipantModel> findByParticipantIdUserId(String userId);
}
