package com.ftr.api.user.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.user.helper.UserHelper;
import com.ftr.api.user.model.GlobalRole;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final RoleService roleService;

    public boolean doesEmailAlreadyExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean doesUsernameAlreadyExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserModel saveNewUser(String firstName, String lastName, String username, String email, String encodedPassword) {
        UserModel userModel = userRepository.save(UserHelper.buildUserModel(firstName, lastName, username, email));
        passwordService.savePassword(encodedPassword, userModel);
        roleService.saveRole(GlobalRole.USER, userModel);
        return userModel;
    }

    public Optional<UserModel> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserModel> findUserByUserId(Integer userId) {
        return userRepository.findById(userId);
    }

}
