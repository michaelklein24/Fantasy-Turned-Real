package com.kleintwins.ftr.show.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class SeasonId implements Serializable {
    @Column(name = "season_sequence")
    private int seasonSequence;

    @Column(name = "show")
    private String show;

    public SeasonId(String show, int seasonSequence) {
        this.seasonSequence = seasonSequence;
        this.show = show;
    }

    public SeasonId() {}
}
