package com.kleintwins.ftr.show.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetShowsResponse {
    List<Show> shows;
}
