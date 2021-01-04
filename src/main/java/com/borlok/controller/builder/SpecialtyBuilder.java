package com.borlok.controller.builder;

import com.borlok.model.Specialty;

public abstract class SpecialtyBuilder {
    private Specialty specialty;

    public void createSpecialty() {
        specialty = new Specialty();
    }

    public abstract void setName(String name);


    public Specialty getSpecialty() {
        return specialty;
    }
}
