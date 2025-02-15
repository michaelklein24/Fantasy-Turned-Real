package com.kleintwins.ftr.show.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shw_contestant_social")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContestantSocialModel {

    @EmbeddedId
    private ContestantSocialId contestantSocialId;

    private String handle;

    @ManyToOne
    @JoinColumn(name = "contestant_id", referencedColumnName = "contestant_id", insertable = false, updatable = false)
    private ContestantModel contestant;
}
