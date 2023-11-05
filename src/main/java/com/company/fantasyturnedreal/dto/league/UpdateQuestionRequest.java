package com.company.fantasyturnedreal.dto.league;

import com.company.fantasyturnedreal.enums.QuestionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionRequest {
    private Long questionId;
    private String questionText;
    private Integer points;
    private QuestionType questionType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> potentialAnswers;
}
