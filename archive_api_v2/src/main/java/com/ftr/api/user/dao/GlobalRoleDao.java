package com.ftr.api.user.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.user.model.GlobalRoleModel;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.repository.GlobalRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GlobalRoleDao implements IDaoImpl<GlobalRoleModel> {

    @Autowired
    private GlobalRoleRepository roleRepository;

    @Override
    public GlobalRoleModel saveEntity(GlobalRoleModel entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Optional<GlobalRoleModel> findEntityById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public void updateEntity(GlobalRoleModel entity) {
        roleRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        roleRepository.deleteById(id);
    }

    public Optional<GlobalRoleModel> getGlobalRoleForUser(Integer userId) {
        return roleRepository.findByUserModelUserId(userId);
    }
}
