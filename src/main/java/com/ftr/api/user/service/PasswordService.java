package com.ftr.api.user.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.user.helper.UserHelper;
import com.ftr.api.user.model.PasswordModel;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.repository.PasswordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordService extends AbstractService {
    private final PasswordRepository passwordRepository;

    public PasswordModel savePassword(String encodedPassword, UserModel userModel) {
        return passwordRepository.save(UserHelper.buildPasswordModel(encodedPassword, userModel));
    }

    public Optional<PasswordModel> getActivePasswordForUser(UserModel userModel) {
        return passwordRepository.findByUserModelAndActive(userModel, true);
    }
}
