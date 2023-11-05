package com.company.fantasyturnedreal.dto.league;

import com.company.fantasyturnedreal.enums.QuestionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionRequest {
    @NotNull
    private String questionText;
    @NotNull
    private Integer points;
    @NotNull
    private QuestionType questionType;
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    private Long episodeId;
    @NotNull
    private Long leagueId;
    private List<String> potentialAnswers;
    @NotNull
    private Long submitterUserId;

}
