package com.ftr.api.show.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shw_show")
public class ShowModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;
    private String show;
}
