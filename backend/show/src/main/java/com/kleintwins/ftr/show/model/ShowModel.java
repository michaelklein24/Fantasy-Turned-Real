package com.kleintwins.ftr.show.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Table(name = "shw_show")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ShowModel {
    @Id
    private String show;
    @OneToMany(mappedBy = "show")
    private List<SeasonModel> seasons;
}
