package com.borlok.service;

import com.borlok.model.Specialty;
import com.borlok.repository.SpecialtyRepository;
import com.borlok.repository.hibernate.JpaSpecialtyRepository;

import java.util.List;

public class SpecialtyService {
    private SpecialtyRepository repository = new JpaSpecialtyRepository();

    public Specialty create(Specialty specialty) {
        return repository.create(specialty);
    }

    public List<Specialty> getAll() {
        return repository.getAll();
    }

    public Specialty update(Specialty specialty, Integer id) {
        return repository.update(specialty, id);
    }

    public void delete(Integer id) {repository.delete(id);}
}
