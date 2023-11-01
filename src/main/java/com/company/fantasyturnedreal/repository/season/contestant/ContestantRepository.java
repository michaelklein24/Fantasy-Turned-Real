package com.company.fantasyturnedreal.repository.season.contestant;

import com.company.fantasyturnedreal.model.contestant.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestantRepository extends JpaRepository<Contestant, Long> {
}
