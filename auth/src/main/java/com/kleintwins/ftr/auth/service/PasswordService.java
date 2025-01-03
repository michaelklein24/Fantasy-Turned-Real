package com.kleintwins.ftr.auth.service;

import com.kleintwins.ftr.auth.exception.InvalidPassword;
import com.kleintwins.ftr.auth.model.PasswordModel;
import com.kleintwins.ftr.auth.model.UserModel;
import com.kleintwins.ftr.auth.repository.PasswordRepository;
import com.kleintwins.ftr.core.service.AbstractService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordService extends AbstractService {

    private final PasswordRepository passwordRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createPasswordForUser(String plainTextPassword, UserModel userModel) {
        // If user is updating password, then mark the previous password as inactive
        Optional<PasswordModel> previousPasswordOpt = passwordRepo.findByUserModelUserIdAndActive(userModel.getUserId(), true);
        previousPasswordOpt.ifPresent(this::markPasswordAsInactive);

        String encodedPassword = encodePassword(plainTextPassword);
        PasswordModel passwordModel = PasswordModel.of(encodedPassword, userModel);
        passwordRepo.save(passwordModel);
    }

    public void validatePassword(String plainTextPassword) throws InvalidPassword {
        Integer minLength = configService.getInt("api.auth.register.password.validation.minLength", 8);
        Integer maxLength = configService.getInt("api.auth.register.password.validation.minLength", 150);

        String validationError = null;
        if (StringUtils.isBlank(plainTextPassword)) {
            validationError = "missingPassword";
        } else if (plainTextPassword.length() < minLength) {
            validationError = "passwordTooShort";
        } else if (plainTextPassword.length() > maxLength) {
            validationError = "passwordTooLong";
        }

        if (validationError != null) {
            String i18nKey = String.format("api.auth.register.response.error.%s.message", validationError);
            throw new InvalidPassword(i18nService.translate(i18nKey));
        }

    }

    private void markPasswordAsInactive(PasswordModel passwordModel) {
        passwordModel.setActive(false);
        passwordRepo.save(passwordModel);
    }

    public Optional<PasswordModel> getActivePasswordforUser(String userId) {
        return passwordRepo.findByUserModelUserIdAndActive(userId, true);
    }

    private String encodePassword(String plainTextPassword) {
        return passwordEncoder.encode(plainTextPassword);
    }
}
