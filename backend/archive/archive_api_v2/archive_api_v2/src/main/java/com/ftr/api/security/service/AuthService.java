package com.ftr.api.security.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.security.code.CheckUsernameResponseTypeCode;
import com.ftr.api.security.dto.*;
import com.ftr.api.user.dao.GlobalRoleDao;
import com.ftr.api.user.dao.PasswordDao;
import com.ftr.api.user.dao.UserDao;
import com.ftr.api.user.model.GlobalRole;
import com.ftr.api.user.model.GlobalRoleModel;
import com.ftr.api.user.model.PasswordModel;
import com.ftr.api.user.model.UserModel;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService extends AbstractService {

    private final UserDao userDao;
    private final PasswordDao passwordDao;
    private final GlobalRoleDao globalRoleDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    @Transactional
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        String email = registerUserRequest.getEmail();
        String username = registerUserRequest.getUsername();
        String firstName = registerUserRequest.getFirstName();
        String lastName = registerUserRequest.getLastName();
        String plainTextPassword = registerUserRequest.getPassword();

        if (userDao.doesUserWithEmailExist(email)) {
            throw new EntityExistsException(String.format("Email '%s' already exists", email));
        }
        if (userDao.doesUserWithUsernameExist(username)) {
            throw new EntityExistsException(String.format("Username '%s' already exists", username));
        }

        UserModel userModel = buildUserModel(firstName, lastName, username, email);
        userModel = userDao.saveEntity(userModel);

        String encodedPassword = encodePassword(plainTextPassword);
        PasswordModel passwordModel = buildPasswordModel(encodedPassword, userModel);
        passwordDao.saveEntity(passwordModel);

        GlobalRoleModel globalRoleModel = buildGlobalRoleModel(userModel);
        globalRoleDao.saveEntity(globalRoleModel);

        Authentication authentication = authenticateUser(username, plainTextPassword);

        return new RegisterUserResponse(generateToken(userModel));
    }

    public LoginUserResponse loginUser(LoginUserRequest request) {
        UserModel userModel = userDao.findUserByUsername(request.getUsername()).orElseThrow(() -> new BadCredentialsException("Bad Credentials"));

        Authentication authentication = authenticateUser(request.getUsername(), request.getPassword());

        return new LoginUserResponse(generateToken(userModel));
    }

    public CheckUsernameResponseTypeCode checkUsername(CheckUsernameRequest request) {
        if (userDao.doesUserWithUsernameExist(request.getUsername())) {
            return CheckUsernameResponseTypeCode.USERNAME_ALREADY_EXISTS;
        }
        return CheckUsernameResponseTypeCode.VALID;
    }

    private UserModel buildUserModel(String firstName, String lastName, String username, String email) {
        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setUsername(username);
        userModel.setEmail(email);
        return userModel;
    }

    private PasswordModel buildPasswordModel(String encodedPassword, UserModel userModel) {
        PasswordModel password = new PasswordModel();
        password.setUserModel(userModel);
        password.setEncodedPassword(encodedPassword);
        password.setActive(true);
        password.setCreatedDate(new Date());
        // TODO: Make this configurable
        password.setExpiryDate(calculateExpiryDate(90));
        return password;
    }

    private Date calculateExpiryDate(Integer daysValid) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 90);
        return calendar.getTime();
    }

    private GlobalRoleModel buildGlobalRoleModel(UserModel userModel) {
        GlobalRoleModel globalRoleModel = new GlobalRoleModel();
        globalRoleModel.setRole(GlobalRole.USER);
        globalRoleModel.setUserModel(userModel);
        return globalRoleModel;
    }

    private String encodePassword(String plainTextPassword) {
        return passwordEncoder.encode(plainTextPassword);
    }

    private String generateToken(UserModel userModel) {
        return jwtGenerator.generateToken(userModel);
    }

    private Authentication authenticateUser(String username, String plainTextPassword) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, plainTextPassword));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }


}
