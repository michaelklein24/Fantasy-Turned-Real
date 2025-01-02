package com.kleintwins.ftr.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ConfigService {
    private final Environment env;

    public String getString(String key, String defaultValue) {
        try {
            return Objects.requireNonNull(env.getProperty(key));
        } catch (NullPointerException e) {
            return defaultValue;
        }
    }

    public int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(Objects.requireNonNull(env.getProperty(key)));
        } catch (NullPointerException e) {
            return defaultValue;
        }
    }

    public long getLong(String key, int defaultValue) {
        try {
            return Long.parseLong(Objects.requireNonNull(env.getProperty(key)));
        } catch (NullPointerException e) {
            return defaultValue;
        }
    }

    public boolean getBool(String key, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(Objects.requireNonNull(env.getProperty(key)));
        } catch (NullPointerException e) {
            return defaultValue;
        }
    }
}

