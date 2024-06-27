package com.ftr.api.user.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.user.helper.UserHelper;
import com.ftr.api.user.model.GlobalRole;
import com.ftr.api.user.model.GlobalRoleModel;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.repository.GlobalRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService extends AbstractService {
    private final GlobalRoleRepository globalRoleRepository;

    public GlobalRoleModel saveRole(GlobalRole role, UserModel userModel) {
        return globalRoleRepository.save(UserHelper.buildRoleModel(role, userModel));
    }

    public Optional<GlobalRoleModel> findByUserModel(UserModel userModel) {
        return globalRoleRepository.findByUserModel(userModel);
    }
}
