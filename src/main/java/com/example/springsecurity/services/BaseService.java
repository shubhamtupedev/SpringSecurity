package com.example.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

@Component
public abstract class BaseService<T, ID extends Serializable> {
    @Autowired
    private JpaRepository<T, ID> repository;

    public T save(T entity) {
        return repository.save(entity);
    }

    public T update(T entity) {
        // You can add custom logic before saving
        return repository.save(entity);
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    public Iterable<T> getAll() {
        return repository.findAll();
    }

    public Page<T> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
