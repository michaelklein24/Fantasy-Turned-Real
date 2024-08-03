package com.company.fantasyturnedreal.dto.contestant;

import com.company.fantasyturnedreal.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateContestantStatusRequest {
    private Long contestantId;
    private Long episodeId;
    private Status status;
    private Long seasonId;
}
