package com.kleintwins.ftr.show.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shw_contestant")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContestantModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "contestant_id")
    private String contestantId;

    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "shw_season_contestant",
            joinColumns = @JoinColumn(name = "contestant_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "season_sequence", referencedColumnName = "season_sequence"),
                    @JoinColumn(name = "show", referencedColumnName = "show")
            }
    )
    private List<SeasonModel> seasons;

    @OneToMany(mappedBy = "contestant", cascade = CascadeType.ALL)
    private List<ContestantStatusModel> statuses;

    @OneToMany(mappedBy = "contestant", cascade = CascadeType.ALL)
    private List<ContestantSocialModel> socials;
}
