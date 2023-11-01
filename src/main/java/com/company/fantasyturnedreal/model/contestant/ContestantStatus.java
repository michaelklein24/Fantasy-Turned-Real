package com.company.fantasyturnedreal.model.contestant;

import com.company.fantasyturnedreal.model.season.Season;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contestant_status")
public class ContestantStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contestantStatusId;

    private Boolean eliminated;
    private Boolean winner;

    @ManyToOne
    @JoinColumn(name = "contestant_id")
    @JsonBackReference("contestant-statuses")
    private Contestant contestant;

    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonBackReference("season-statuses")
    private Season season;

}
