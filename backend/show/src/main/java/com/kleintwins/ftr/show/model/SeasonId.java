package com.kleintwins.ftr.show.model;

import com.kleintwins.ftr.show.code.Show;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class SeasonId implements Serializable {
    @Column(name = "season_sequence")
    private int seasonSequence;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "show")
    private Show show;

    public SeasonId(Show show, int seasonSequence) {
        this.seasonSequence = seasonSequence;
        this.show = show;
    }

    public SeasonId() {}
}
