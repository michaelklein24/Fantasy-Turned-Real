package com.ftr.api.user.dto;

import com.ftr.api.user.model.UserModel;
import lombok.Data;

@Data
public class GetUserDetailsResponse {
    private UserModel userModel;

    public GetUserDetailsResponse(UserModel userModel) {
        this.userModel = userModel;
    }
}
