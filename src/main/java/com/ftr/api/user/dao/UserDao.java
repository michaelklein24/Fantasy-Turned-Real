package com.ftr.api.user.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDao implements IDaoImpl<UserModel> {

    private final UserRepository userRepository;

    @Override
    public UserModel saveEntity(UserModel entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<UserModel> findEntityById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateEntity(UserModel entity) {
        userRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        userRepository.deleteById(id);
    }

    public Optional<UserModel> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserModel> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean doesUserWithEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean doesUserWithUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }
}
