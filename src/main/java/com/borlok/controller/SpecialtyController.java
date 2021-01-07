package com.borlok.controller;

import com.borlok.model.Specialty;
import com.borlok.service.SpecialtyService;

import java.util.List;

public class SpecialtyController {
    private SpecialtyService specialtyService = new SpecialtyService();

    public Specialty create(Specialty specialty) {
        return specialtyService.create(specialty);
    }

    public List<Specialty> getAll() {
        return specialtyService.getAll();
    }

    public Specialty getById(int id) {
        return specialtyService.getById(id);
    }

    public Specialty update(Specialty specialty) {
        return specialtyService.update(specialty);
    }

    public void delete(Integer id) {
        specialtyService.delete(id);
    }
}
