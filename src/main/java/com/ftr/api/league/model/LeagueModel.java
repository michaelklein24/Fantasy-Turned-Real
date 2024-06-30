package com.ftr.api.league.model;

import com.ftr.api.league.code.LeagueStatusCode;
import jakarta.persistence.*;
import jakarta.servlet.http.Part;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "lge_league")
public class LeagueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leagueId;

    private String name;

    private LeagueStatusCode status;

    @OneToMany(mappedBy = "participantId", cascade = CascadeType.ALL)
    List<ParticipantModel> participantModels;

    public boolean isCompleted() {
        return this.status.equals(LeagueStatusCode.COMPLETED);
    }
    public boolean isNotCompleted() {
        return !this.isCompleted();
    }
}
