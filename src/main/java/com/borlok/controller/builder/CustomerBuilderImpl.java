package com.borlok.controller.builder;

import com.borlok.model.Account;
import com.borlok.model.Specialty;

import java.util.Set;

public class CustomerBuilderImpl extends CustomerBuilder {
    @Override
    public void setAccount(Account account) {
        getCustomer().setAccount(account);
    }

    @Override
    public void setSpecialties(Set<Specialty> specialties) {
        getCustomer().setSpecialties(specialties);
    }
}
