package com.kleintwins.ftr.show.model;

import com.kleintwins.ftr.show.code.ContestantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "shw_contestant_status")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContestantStatusModel {

    @EmbeddedId
    private ContestantStatusId contestantStatusId;

    @ManyToOne
    @JoinColumn(name = "contestant_id", referencedColumnName = "contestant_id", insertable = false, updatable = false)
    private ContestantModel contestant;

    @Enumerated(EnumType.STRING)
    private ContestantStatus status;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "episode_sequence", referencedColumnName = "episode_sequence", insertable = false, updatable = false),
            @JoinColumn(name = "season_sequence", referencedColumnName = "season_sequence",  insertable = false, updatable = false),
            @JoinColumn(name = "show_id", referencedColumnName = "show",  insertable = false, updatable = false)
    })
    private EpisodeModel episode;

    private LocalDate statusChangeDate;
}
