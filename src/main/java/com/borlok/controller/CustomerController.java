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

    public Customer update(Customer customer, Integer id) {
        return customerService.update(customer, id);
    }

    public void delete(Integer id) {
        customerService.delete(id);
    }
}
