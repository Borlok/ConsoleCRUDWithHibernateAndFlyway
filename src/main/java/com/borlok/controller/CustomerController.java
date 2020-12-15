package com.borlok.controller;

import com.borlok.model.Customer;
import com.borlok.service.CompositeService;
import com.borlok.service.CustomerService;

import java.util.List;

public class CustomerController implements Controller<Customer> {
    private CompositeService compositeService;

    public CustomerController() {
    }

    public CustomerController(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @Override
    public Customer create(Customer customer) {
        return ((CustomerService) compositeService.getService(new CustomerService())).create(customer);
    }

    @Override
    public List<Customer> getAll() {
        return ((CustomerService) compositeService.getService(new CustomerService())).getAll();
    }

    @Override
    public Customer update(Customer customer, Integer id) {
        return ((CustomerService) compositeService.getService(new CustomerService())).update(customer, id);
    }

    @Override
    public void delete(Integer id) {
        compositeService.getService(new CustomerService()).delete(id);
    }

    @Override
    public String toString() {
        return "CustomerController";
    }
}
