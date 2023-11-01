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
public class CreateSeasonRequest {
    @NotNull
    @Size(min = 3)
    private String title;
    @NotNull
    private Show show;
    @NotNull
    private Long seasonNumber;
}
