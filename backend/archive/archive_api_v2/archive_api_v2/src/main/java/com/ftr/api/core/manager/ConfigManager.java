package com.ftr.api.core.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ConfigManager {
    @Autowired
    protected Environment env;

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
