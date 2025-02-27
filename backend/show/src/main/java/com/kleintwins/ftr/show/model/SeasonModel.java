package com.kleintwins.ftr.show.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Table(name = "ssn_season")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SeasonModel {
    @EmbeddedId
    private SeasonId seasonId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int totalEpisodes;

    @ManyToMany(mappedBy = "seasons", fetch = FetchType.EAGER)
    private List<ContestantModel> contestants;

    @OneToMany(mappedBy = "season")
    private List<EpisodeModel> episodes;

    @ManyToOne
    @JoinColumn(name = "show", referencedColumnName = "show", insertable = false, updatable = false)
    private ShowModel show;
}
