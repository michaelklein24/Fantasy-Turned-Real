package com.kleintwins.ftr.show.dto;

import com.kleintwins.ftr.show.code.Show;
import lombok.Data;

import java.util.List;

@Data
public class GetShowsResponse {
    List<Show> shows;
}
