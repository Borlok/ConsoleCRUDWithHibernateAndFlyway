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

    public Specialty update(Specialty specialty, Integer id) {
        return specialtyService.update(specialty, id);
    }

    public void delete(Integer id) {
        specialtyService.delete(id);
    }
}
