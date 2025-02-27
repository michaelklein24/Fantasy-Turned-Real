package com.kleintwins.ftr.show.repository;

import com.kleintwins.ftr.show.model.ShowModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowModel, String> {
}
