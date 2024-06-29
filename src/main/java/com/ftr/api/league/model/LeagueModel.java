package com.ftr.api.league.model;

import com.ftr.api.league.code.LeagueStatusCode;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lge_league")
public class LeagueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leagueId;

    private String name;

    private LeagueStatusCode status;

    public boolean isCompleted() {
        return this.status.equals(LeagueStatusCode.COMPLETED);
    }
    public boolean isNotCompleted() {
        return !this.isCompleted();
    }
}
