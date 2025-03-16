package com.kleintwins.ftr.core.repository;

import com.kleintwins.ftr.core.model.SequenceId;
import com.kleintwins.ftr.core.model.SequenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends JpaRepository<SequenceModel, SequenceId> {
}
