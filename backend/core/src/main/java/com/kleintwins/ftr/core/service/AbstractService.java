package com.kleintwins.ftr.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractService {

    @Autowired
    protected I18nService i18nService;
    @Autowired
    protected ConfigService configService;

    protected <T> void applyIfNotNull(T value, Consumer<T> consumer) {
        Optional.ofNullable(value).ifPresent(consumer);
    }
}
