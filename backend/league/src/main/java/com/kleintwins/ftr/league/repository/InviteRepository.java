package com.kleintwins.ftr.league.repository;

import com.kleintwins.ftr.league.model.InviteId;
import com.kleintwins.ftr.league.model.InviteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends JpaRepository<InviteModel, InviteId> {
}
