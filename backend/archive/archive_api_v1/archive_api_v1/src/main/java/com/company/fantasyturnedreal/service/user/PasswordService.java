package com.company.fantasyturnedreal.service.user;

import com.company.fantasyturnedreal.model.user.Password;
import com.company.fantasyturnedreal.repository.user.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    PasswordRepository passwordRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public Password savePassword(String plainPassword) {
        Password password = new Password();
//        password.setEncryptedPassword(passwordEncoder.encode(plainPassword));
        return passwordRepo.save(password);
    }

    public boolean isPasswordMatch(Long userId, String plainPassword) {

        return false;
    }

}
