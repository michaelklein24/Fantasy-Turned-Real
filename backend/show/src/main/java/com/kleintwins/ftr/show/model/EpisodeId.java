package com.kleintwins.ftr.show.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class EpisodeId implements Serializable {
    @Column(name = "episode_sequence")
    private int episodeSequence;
    @Embedded
    private SeasonId seasonId;

    public EpisodeId(int episodeSequence, SeasonId seasonId) {
        this.episodeSequence = episodeSequence;
        this.seasonId = seasonId;
    }

    public EpisodeId() {}
}