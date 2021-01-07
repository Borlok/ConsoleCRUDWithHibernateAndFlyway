package com.borlok.controller;

import com.borlok.model.Customer;
import com.borlok.service.CustomerService;

import java.util.List;

public class CustomerController {
    private CustomerService customerService = new CustomerService();

    public Customer create(Customer customer) {
        return customerService.create(customer);
    }

    public List<Customer> getAll() {
        return customerService.getAll();
    }

    public Customer getById(int id) {
        return customerService.getById(id);
    }

    public Customer update(Customer customer) {
        return customerService.update(customer);
    }

    public void delete(Integer id) {
        customerService.delete(id);
    }
}
