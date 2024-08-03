package com.company.fantasyturnedreal.dto.league;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAnswerRequest {
    private String answer;
    private Long userId;
    private Long questionId;
}
