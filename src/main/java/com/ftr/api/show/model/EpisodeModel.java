package com.ftr.api.show.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shw_episode")
public class EpisodeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer episodeId;

    private String title;

    private Integer episodeNumber;
}
