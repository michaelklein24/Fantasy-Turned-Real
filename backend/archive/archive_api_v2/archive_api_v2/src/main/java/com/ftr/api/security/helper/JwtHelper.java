package com.ftr.api.security.helper;

import com.ftr.api.core.manager.ConfigManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtHelper {

    @Autowired
    private ConfigManager configManager;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Integer extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("userId", Integer.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(
                Keys.hmacShaKeyFor(configManager.getString("api.jwt.secret", "")
                        .getBytes(StandardCharsets.UTF_8)
                )).build();
        return parser.parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        boolean ignoreExpiration = configManager.getBool("api.jwt.ignoreExpiration", true);
        if (ignoreExpiration) {
            return false;
        }
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
