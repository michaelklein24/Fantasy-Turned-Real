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
public class UpdateContestantStatusRequest {
    private Long contestantStatusId;
    private Status status;
}
