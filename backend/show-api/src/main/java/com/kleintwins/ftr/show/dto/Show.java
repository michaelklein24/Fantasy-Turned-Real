package com.kleintwins.ftr.show.dto;

import lombok.Data;

import java.util.List;

@Data
public class Show {
    private String name;
    private List<Season> seasons;
}
