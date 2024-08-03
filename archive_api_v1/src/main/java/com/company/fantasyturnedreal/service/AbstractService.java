package com.company.fantasyturnedreal.service;

import java.util.function.Consumer;

public abstract class AbstractService {
    protected <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
