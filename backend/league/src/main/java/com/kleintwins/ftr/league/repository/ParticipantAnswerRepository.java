package com.kleintwins.ftr.league.repository;

import com.kleintwins.ftr.league.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantAnswerRepository extends JpaRepository<ParticipantAnswerModel, ParticipantAnswerId> {
    Optional<ParticipantAnswerModel> findByParticipantAndQuestion(ParticipantModel participantModel, QuestionModel questionModel);
}
