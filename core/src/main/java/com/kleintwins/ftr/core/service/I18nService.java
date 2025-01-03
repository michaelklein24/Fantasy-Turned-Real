package com.kleintwins.ftr.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class I18nService {
    private final MessageSource messageSource;

    public String translate(String key) {
        return translate(key, null);
    }

    public String translate(String key, String... args) {
        try {
            return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return "<MissingTranslation key: " + key + " />";
        }
    }
}

