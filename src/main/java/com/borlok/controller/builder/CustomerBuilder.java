package com.borlok.controller.builder;

import com.borlok.model.Account;
import com.borlok.model.Customer;
import com.borlok.model.Specialty;

import java.util.Set;

public abstract class CustomerBuilder {
    private Customer customer;

    public void createCustomer() {
        customer = new Customer();
    }

    public abstract void setAccount(Account account);
    public abstract void setSpecialties(Set<Specialty> specialties);

    public Customer getCustomer() {
        return customer;
    }
}
