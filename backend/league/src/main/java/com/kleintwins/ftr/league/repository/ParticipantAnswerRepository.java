package com.kleintwins.ftr.league.repository;

import com.kleintwins.ftr.league.model.ParticipantAnswerId;
import com.kleintwins.ftr.league.model.ParticipantAnswerModel;
import com.kleintwins.ftr.league.model.ParticipantId;
import com.kleintwins.ftr.league.model.QuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantAnswerRepository extends JpaRepository<ParticipantAnswerModel, ParticipantAnswerId> {
    Optional<ParticipantAnswerModel> findByParticipantParticipantIdAndQuestionQuestionId(ParticipantId participantId, QuestionId questionId);
}
