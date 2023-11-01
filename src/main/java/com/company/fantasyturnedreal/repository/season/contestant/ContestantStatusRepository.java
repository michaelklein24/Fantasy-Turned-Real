package com.company.fantasyturnedreal.repository.season.contestant;

import com.company.fantasyturnedreal.model.contestant.ContestantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestantStatusRepository extends JpaRepository<ContestantStatus, Long> {

}
