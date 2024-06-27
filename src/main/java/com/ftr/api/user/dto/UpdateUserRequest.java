package com.ftr.api.user.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
