package com.kleintwins.ftr.league.repository;

import com.kleintwins.ftr.league.model.QuestionId;
import com.kleintwins.ftr.league.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionModel, QuestionId> {
}
