package com.kleintwins.ftr.auth.service;

import com.kleintwins.ftr.core.exception.EntityNotFound;
import com.kleintwins.ftr.core.service.AbstractService;
import com.kleintwins.ftr.user.code.GlobalRole;
import com.kleintwins.ftr.user.model.UserModel;
import com.kleintwins.ftr.user.service.PasswordService;
import com.kleintwins.ftr.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl extends AbstractService implements UserDetailsService{
    private final UserService userService;
    private final PasswordService passwordService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserModel userModel = userService.findUserByUserId(userId)
                .orElseThrow(() -> new EntityNotFound("User not found with userId: " + userId));
        return new User(userModel.getUserId(), passwordService.getActivePasswordforUser(userModel.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Active password not found with userId: " + userModel.getUserId()))
                .getEncodedPassword(),
                mapRolesToAuthorities(userModel.getRole()));
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(GlobalRole role) {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }
}
