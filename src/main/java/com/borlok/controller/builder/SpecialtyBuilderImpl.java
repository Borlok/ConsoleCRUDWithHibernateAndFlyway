package com.borlok.controller.builder;

public class SpecialtyBuilderImpl extends SpecialtyBuilder {
    @Override
    public void setName(String name) {
        getSpecialty().setName(name);
    }
}
