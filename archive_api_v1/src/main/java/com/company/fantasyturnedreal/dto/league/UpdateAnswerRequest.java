package com.company.fantasyturnedreal.dto.league;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAnswerRequest {
    private Long answerId;
    private String answer;
}
