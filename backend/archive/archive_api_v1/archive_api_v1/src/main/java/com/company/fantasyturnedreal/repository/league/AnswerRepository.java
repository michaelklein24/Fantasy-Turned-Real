package com.company.fantasyturnedreal.repository.league;

import com.company.fantasyturnedreal.model.league.Answer;
import com.company.fantasyturnedreal.model.league.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {
}
