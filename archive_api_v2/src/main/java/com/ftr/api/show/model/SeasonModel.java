package com.ftr.api.show.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ssn_episode")
public class SeasonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seasonId;
}
