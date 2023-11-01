package com.company.fantasyturnedreal.service.user;

import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.model.user.User;
import com.company.fantasyturnedreal.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public User getUserById(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new DataNotFoundException(String.format("User with id '%s' does not exist.", userId));
        }
        return user;
    }
}
