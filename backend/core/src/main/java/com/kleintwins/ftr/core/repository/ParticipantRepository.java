package com.kleintwins.ftr.core.repository;

import com.kleintwins.ftr.core.model.ParticipantId;
import com.kleintwins.ftr.core.model.ParticipantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantModel, ParticipantId> {
}
