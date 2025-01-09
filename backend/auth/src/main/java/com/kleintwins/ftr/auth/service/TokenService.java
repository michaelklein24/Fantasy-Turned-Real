package com.kleintwins.ftr.auth.service;


import com.kleintwins.ftr.core.service.AbstractService;
import com.kleintwins.ftr.user.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService extends AbstractService {

    public String generateToken(UserModel userModel) {
        String jwtSecret = configService.getString("api.jwt.secret", "");

        Date currentDate = new Date();
        Long tokenLifetimeLength = configService.getLong("api.jwt.expiration", 600000);
        Date expiryDate = new Date(currentDate.getTime() + tokenLifetimeLength);

        boolean isTokenExpirationEnabled = configService.getBool("api.jwt.expiration.enabled", true);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userModel.getUserId());
        claims.put("email", userModel.getEmail());

        return Jwts.builder()
                .setIssuedAt(currentDate)
                .setClaims(claims)
                .setExpiration(isTokenExpirationEnabled ? expiryDate : null)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
