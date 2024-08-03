package com.ftr.api.security.service;

import com.ftr.api.core.manager.ConfigManager;
import com.ftr.api.user.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtGenerator {

    protected ConfigManager configManager;

    @Autowired
    public JwtGenerator(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public String generateToken(UserModel userModel) {
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + configManager.getLong("api.jwt.expiration", 60000));
        boolean isTokenExpirationEnabled = configManager.getBool("api.jwt.expiration.enabled", false);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userModel.getUserId());
        claims.put("sub", userModel.getUsername());

        return Jwts.builder()
                .setIssuedAt(currentDate)
                .setClaims(claims)
                .setExpiration(isTokenExpirationEnabled ? expiryDate : null)
                .signWith(Keys.hmacShaKeyFor(configManager.getString("api.jwt.secret", "").getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
