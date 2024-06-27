package com.ftr.api.core.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class I18nManager {

    private final MessageSource messageSource;

    public String getString(Locale locale, String key, String... args) {
        String message;
        try {
            message = messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            message = String.format("Missing translation: %s", key);
        }
        return message;
    }

    public String getString(String key, String... args) {
        return getString(Locale.ENGLISH, key, args);
    }

}
