package com.ftr.api.core.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDaoImpl<T> {

    T saveEntity(T entity);
    Optional<T> findEntityById(Integer id);
    void updateEntity(T entity);
    void deleteEntityById(Integer id);
}
