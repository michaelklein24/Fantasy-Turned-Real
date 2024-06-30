package com.ftr.api.user.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.user.model.PasswordModel;
import com.ftr.api.user.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordDao implements IDaoImpl<PasswordModel> {

    @Autowired
    private PasswordRepository passwordRepository;

    @Override
    public PasswordModel saveEntity(PasswordModel entity) {
        return passwordRepository.save(entity);
    }

    @Override
    public Optional<PasswordModel> findEntityById(Integer id) {
        return passwordRepository.findById(id);
    }

    @Override
    public void updateEntity(PasswordModel entity) {
        passwordRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        passwordRepository.deleteById(id);
    }

    public Optional<PasswordModel> getActivePasswordforUser(Integer userId) {
        return passwordRepository.findByUserModelUserIdAndActive(userId, true);
    }
}
