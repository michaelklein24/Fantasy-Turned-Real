package com.company.fantasyturnedreal.dto.contestant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateContestantRequest {
    private String firstName;
    private String lastName;
    private String occupation;
    private String originCity;
    private String originState;
    private Date birthday;
    private String nickname;
    private String biography;
    private List<Long> seasons = new ArrayList<>();
}
