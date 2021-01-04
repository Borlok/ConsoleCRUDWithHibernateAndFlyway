package com.borlok.service;

import com.borlok.model.Customer;
import com.borlok.repository.CompositeRepository;
import com.borlok.repository.hibernate.JpaCustomerRepository;

import java.util.List;

public class CustomerService implements Service<Customer> {
    private CompositeRepository repository;

    public CustomerService() {
    }

    public CustomerService(CompositeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(Customer customer) {
        return ((JpaCustomerRepository) repository
                .getRepository(new JpaCustomerRepository())).create(customer);
    }

    @Override
    public List<Customer> getAll() {
        return ((JpaCustomerRepository) repository
                .getRepository(new JpaCustomerRepository())).getAll();
    }

    @Override
    public Customer update(Customer customer, Integer id) {
        return ((JpaCustomerRepository) repository
                .getRepository(new JpaCustomerRepository())).update(customer, id);
    }

    @Override
    public void delete(Integer id) {
        repository
                .getRepository(new JpaCustomerRepository()).delete(id);
    }

    @Override
    public String toString() {
        return "CustomerService";
    }
}
