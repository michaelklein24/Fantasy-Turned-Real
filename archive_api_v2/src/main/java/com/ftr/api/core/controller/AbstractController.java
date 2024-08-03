package com.ftr.api.core.controller;

import com.ftr.api.core.manager.ConfigManager;
import com.ftr.api.core.manager.I18nManager;
import com.ftr.api.security.exception.BadJwtException;
import com.ftr.api.security.helper.JwtHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractController {
    @Autowired
    protected ConfigManager configManager;
    @Autowired
    protected I18nManager i18nManager;
    @Autowired
    protected JwtHelper jwtHelper;

    protected Integer extractUserIdFromToken(String bearerToken) {
        if (doesRequestContainValidToken(bearerToken)) {
            String token = bearerToken.substring(7);
            Integer userId = jwtHelper.extractUserId(token);
            if (userId != null) {
                return userId;
            }
        }
        throw new BadJwtException("Request does not contain a valid JSON Web Token.");
    }

    private boolean doesRequestContainValidToken(String bearerToken) {
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ");
    }
}
