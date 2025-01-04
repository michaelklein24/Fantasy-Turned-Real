package com.company.fantasyturnedreal.service.user;

import com.company.fantasyturnedreal.dto.user.RegisterUserRequest;
import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.model.user.User;
import com.company.fantasyturnedreal.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordService passwordService;

    @Transactional
    public User registerNewUser(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(passwordService.savePassword(registerUserRequest.getPassword()));
        user.setEmail(registerUserRequest.getEmail());
        return userRepo.save(user);
    }

    public User getUserById(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new DataNotFoundException(String.format("User with id '%s' does not exist.", userId));
        }
        return user;
    }
}
