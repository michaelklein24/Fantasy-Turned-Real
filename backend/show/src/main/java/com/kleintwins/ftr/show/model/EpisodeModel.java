package com.kleintwins.ftr.show.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "ssn_episode")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EpisodeModel {
    @EmbeddedId
    private EpisodeId episodeId;
    private String name;
    private LocalDateTime airTime;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "show", referencedColumnName = "show", insertable = false, updatable = false),
            @JoinColumn(name = "season_sequence", referencedColumnName = "season_sequence", insertable = false, updatable = false)
    })
    private SeasonModel season;
}
