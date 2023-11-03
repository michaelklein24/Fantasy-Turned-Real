package com.company.fantasyturnedreal.controller.user;

import com.company.fantasyturnedreal.dto.user.RegisterUserRequest;
import com.company.fantasyturnedreal.model.user.User;
import com.company.fantasyturnedreal.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.company.fantasyturnedreal.util.RestApiSupport.REST_API_CONTEXT_PATH;

@RestController
@RequestMapping(REST_API_CONTEXT_PATH + "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody RegisterUserRequest request) {
        return userService.registerNewUser(request);
    }
}
