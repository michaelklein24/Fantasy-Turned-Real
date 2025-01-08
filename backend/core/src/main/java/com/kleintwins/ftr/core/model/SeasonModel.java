package com.kleintwins.ftr.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "ssn_season")
@Entity
@Getter
@Setter
@ToString(exclude = "leagues")
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
    @OneToMany(mappedBy = "season", cascade = CascadeType.DETACH)
    private List<LeagueModel> leagues;
}
