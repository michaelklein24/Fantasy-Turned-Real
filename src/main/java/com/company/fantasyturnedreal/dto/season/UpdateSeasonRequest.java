package com.company.fantasyturnedreal.dto.season;

import com.company.fantasyturnedreal.enums.Show;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSeasonRequest {
    @NotNull(message = "seasonId must not be null")
    private Long seasonId;
    @Size(min = 3, message = "title must be at least 3 chars long")
    private String title;
    private Show show;
    private Long seasonNumber;
}
