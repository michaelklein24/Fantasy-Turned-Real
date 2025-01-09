package com.kleintwins.ftr.auth.dto;

import com.kleintwins.ftr.user.code.GlobalRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
