package com.ftr.api.core.service;

import com.ftr.api.core.manager.I18nManager;

import java.util.Base64;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractService {
    protected static <T> void applyIfNotNull(T value, Consumer<T> consumer) {
        Optional.ofNullable(value).ifPresent(consumer);
    }

    public String getImageBase64(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}
