package com.ftr.api.security.service;

import com.ftr.api.user.model.GlobalRoleModel;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.repository.GlobalRoleRepository;
import com.ftr.api.user.repository.PasswordRepository;
import com.ftr.api.user.repository.UserRepository;
import com.ftr.api.user.service.PasswordService;
import com.ftr.api.user.service.RoleService;
import com.ftr.api.user.service.UserService;
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
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordService passwordService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userService.findUserByUsername(username)
                .orElseThrow(() ->  new UsernameNotFoundException("User not found with username: " + username));
        GlobalRoleModel roleModel = roleService.findByUserModel(userModel)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with userId: " + userModel.getUserId()));
        return new User(userModel.getUsername(), passwordService.getActivePasswordForUser(userModel)
                .orElseThrow(() -> new EntityNotFoundException("Active password not found with userId: " + userModel.getUserId()))
                .getEncodedPassword(), mapRolesToAuthorities(List.of(roleModel)));
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(List<GlobalRoleModel> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).toList();
    }
}
