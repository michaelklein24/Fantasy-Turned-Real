package com.ftr.api.user.dto;

import com.ftr.api.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserResponse {
    private UserModel userModelDetails;
}
