package com.company.fantasyturnedreal.dto.season;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEpisodeRequest {
    private String title;
    private Date airDate;
    private Long seasonId;
}
