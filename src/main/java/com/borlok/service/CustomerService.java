package com.borlok.service;

import com.borlok.model.Customer;
import com.borlok.repository.CompositeRepository;
import com.borlok.repository.JDBC.JdbcCustomerRepository;

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
        return ((JdbcCustomerRepository) repository
                .getRepository(new JdbcCustomerRepository())).create(customer);
    }

    @Override
    public List<Customer> getAll() {
        return ((JdbcCustomerRepository) repository
                .getRepository(new JdbcCustomerRepository())).getAll();
    }

    @Override
    public Customer update(Customer customer, Integer id) {
        return ((JdbcCustomerRepository) repository
                .getRepository(new JdbcCustomerRepository())).update(customer, id);
    }

    @Override
    public void delete(Integer id) {
        repository
                .getRepository(new JdbcCustomerRepository()).delete(id);
    }

    @Override
    public String toString() {
        return "CustomerService";
    }
}
