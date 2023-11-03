package com.company.fantasyturnedreal.repository.league;

import com.company.fantasyturnedreal.model.contestant.ContestantStatus;
import com.company.fantasyturnedreal.model.league.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

}
