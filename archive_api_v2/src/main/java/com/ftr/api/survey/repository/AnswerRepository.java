package com.ftr.api.survey.repository;

import com.ftr.api.survey.model.AnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerModel, Integer> {
    Optional<AnswerModel> findByUserModelUserIdAndQuestionModelQuestionId(Integer userId, Integer questionId);
}
