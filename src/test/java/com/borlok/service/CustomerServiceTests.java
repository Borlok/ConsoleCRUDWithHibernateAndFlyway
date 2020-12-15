package com.borlok.service;

import com.borlok.model.Account;
import com.borlok.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTests {
    private   String name = "Check";

    @Mock
    private Customer customer;

    @Mock
    private Account account;

    @Mock
    private Customer returnedCustomer;

    @Mock
    private List<Customer> customers;

    @Spy
    private CustomerService customerService;

    @Before
    public void setUp() {
        when(customer.getAccount()).thenReturn(account);
        when(account.getName()).thenReturn(name);
    }

    @Test
    public void getSpecialtyTests() {
        assertEquals(name, customer.getAccount().getName());
        assertEquals(0,customer.getId());
    }

    @Test
    public void createTest () {
        doReturn(returnedCustomer).when(customerService).create(customer);
        assertEquals(returnedCustomer, customerService.create(customer));
    }

    @Test
    public void getAllTest () {
        doReturn(customers).when(customerService).getAll();
        assertEquals(customers, customerService.getAll());
    }

    @Test
    public void updateTest () {
        doReturn(returnedCustomer).when(customerService).update(customer,15);
        assertEquals(returnedCustomer, customerService.update(customer, 15));
    }
}
