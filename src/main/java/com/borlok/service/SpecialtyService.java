package com.borlok.service;

import com.borlok.model.Specialty;
import com.borlok.repository.CompositeRepository;
import com.borlok.repository.JDBC.JdbcSpecialtyRepository;

import java.util.List;

public class SpecialtyService implements Service<Specialty> {
    private CompositeRepository repository;

    public SpecialtyService() {
    }

    public SpecialtyService(CompositeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Specialty create(Specialty specialty) {
        return ((JdbcSpecialtyRepository) repository
                .getRepository(new JdbcSpecialtyRepository())).create(specialty);
    }

    @Override
    public List<Specialty> getAll() {
        return ((JdbcSpecialtyRepository) repository
                .getRepository(new JdbcSpecialtyRepository())).getAll();
    }

    @Override
    public Specialty update(Specialty specialty, Integer id) {
        return ((JdbcSpecialtyRepository) repository
                .getRepository(new JdbcSpecialtyRepository())).update(specialty, id);
    }

    @Override
    public void delete(Integer id) {
        repository
                .getRepository(new JdbcSpecialtyRepository()).delete(id);
    }

    @Override
    public String toString() {
        return "SpecialtyService";
    }
}
