package com.kleintwins.ftr.auth.util;

import com.kleintwins.ftr.auth.dto.User;
import com.kleintwins.ftr.user.model.UserModel;

public class UserDtoBuilder {
    public static User buildUser(UserModel userModel) {
        return User.builder()
                .userId(userModel.getUserId())
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .build();
    }
}
