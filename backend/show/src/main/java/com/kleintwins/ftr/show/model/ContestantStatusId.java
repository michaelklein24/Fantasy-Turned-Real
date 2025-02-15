package com.kleintwins.ftr.show.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ContestantStatusId implements Serializable {
    @Embedded
    private EpisodeId episodeId;
    @Column(name = "contestant_id")
    private String contestantId;
}
