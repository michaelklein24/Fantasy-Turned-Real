package com.ftr.api.security.service;

import com.ftr.api.user.dao.GlobalRoleDao;
import com.ftr.api.user.dao.PasswordDao;
import com.ftr.api.user.dao.UserDao;
import com.ftr.api.user.model.GlobalRoleModel;
import com.ftr.api.user.model.UserModel;
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
    private final UserDao userDao;
    private final GlobalRoleDao globalRoleDao;
    private final PasswordDao passwordDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userDao.findUserByUsername(username)
                .orElseThrow(() ->  new UsernameNotFoundException("User not found with username: " + username));
        GlobalRoleModel roleModel = globalRoleDao.getGlobalRoleForUser(userModel.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found with userId: " + userModel.getUserId()));
        return new User(userModel.getUsername(), passwordDao.getActivePasswordforUser(userModel.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Active password not found with userId: " + userModel.getUserId()))
                .getEncodedPassword(), mapRolesToAuthorities(List.of(roleModel)));
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(List<GlobalRoleModel> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).toList();
    }
}
