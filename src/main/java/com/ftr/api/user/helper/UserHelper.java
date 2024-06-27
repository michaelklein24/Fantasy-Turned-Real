package com.ftr.api.user.helper;

import com.ftr.api.user.model.GlobalRole;
import com.ftr.api.user.model.GlobalRoleModel;
import com.ftr.api.user.model.PasswordModel;
import com.ftr.api.user.model.UserModel;

import java.util.Calendar;
import java.util.Date;

public class UserHelper {

    public static UserModel buildUserModel(String firstName, String lastName, String username, String email) {
        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setUsername(username);
        userModel.setEmail(email);
        return userModel;
    }

    public static GlobalRoleModel buildRoleModel(GlobalRole role, UserModel userModel) {
        GlobalRoleModel globalRoleModel = new GlobalRoleModel();
        globalRoleModel.setUserModel(userModel);
        globalRoleModel.setRole(role);
        return globalRoleModel;
    }

    public static PasswordModel buildPasswordModel(String encodedPassword, UserModel userModel) {
        PasswordModel password = new PasswordModel();
        password.setUserModel(userModel);
        password.setEncodedPassword(encodedPassword);
        password.setActive(true);
        password.setCreatedDate(new Date());
        // TODO: Make this configurable
        password.setExpiryDate(calculateExpiryDate(90));
        return password;
    }

    public static Date calculateExpiryDate(Integer daysValid) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 90);
        return calendar.getTime();
    }
}
