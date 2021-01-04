package com.borlok.service;

import com.borlok.model.Specialty;
import com.borlok.repository.CompositeRepository;
import com.borlok.repository.hibernate.JpaSpecialtyRepository;

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
        return ((JpaSpecialtyRepository) repository
                .getRepository(new JpaSpecialtyRepository())).create(specialty);
    }

    @Override
    public List<Specialty> getAll() {
        return ((JpaSpecialtyRepository) repository
                .getRepository(new JpaSpecialtyRepository())).getAll();
    }

    @Override
    public Specialty update(Specialty specialty, Integer id) {
        return ((JpaSpecialtyRepository) repository
                .getRepository(new JpaSpecialtyRepository())).update(specialty, id);
    }

    @Override
    public void delete(Integer id) {
        repository
                .getRepository(new JpaSpecialtyRepository()).delete(id);
    }

    @Override
    public String toString() {
        return "SpecialtyService";
    }
}
