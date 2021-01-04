package com.borlok.controller;

import com.borlok.model.Specialty;
import com.borlok.service.CompositeService;
import com.borlok.service.SpecialtyService;

import java.util.List;

public class SpecialtyController implements Controller<Specialty> {
    private CompositeService compositeService;

    public SpecialtyController() {
    }

    public SpecialtyController(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @Override
    public Specialty create(Specialty specialty) {
        return ((SpecialtyService) compositeService
                .getService(new SpecialtyService())).create(specialty);
    }

    @Override
    public List<Specialty> getAll() {
        return ((SpecialtyService) compositeService
                .getService(new SpecialtyService())).getAll();
    }

    @Override
    public Specialty update(Specialty specialty, Integer id) {
        return ((SpecialtyService) compositeService
                .getService(new SpecialtyService())).update(specialty, id);
    }

    @Override
    public void delete(Integer id) {
        compositeService
                .getService(new SpecialtyService()).delete(id);
    }

    @Override
    public String toString() {
        return "SpecialtyController";
    }
}
