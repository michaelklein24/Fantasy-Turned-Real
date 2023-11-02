package com.company.fantasyturnedreal.model.contestant;

import com.company.fantasyturnedreal.enums.Status;
import com.company.fantasyturnedreal.model.season.Episode;
import com.company.fantasyturnedreal.model.season.Season;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contestant_status")
public class ContestantStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contestantStatusId;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "contestant_id")
    @JsonManagedReference("contestant-statuses")
    private Contestant contestant;

    @ManyToOne
    @JoinColumn(name = "episode_id")
    @JsonManagedReference("episode-statuses")
    private Episode episode;

    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonManagedReference("season-statuses")
    private Season season;

}
