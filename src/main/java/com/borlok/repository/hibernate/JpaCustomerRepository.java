package com.borlok.repository.hibernate;

import com.borlok.model.Account;
import com.borlok.model.Customer;
import com.borlok.repository.CustomerRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class JpaCustomerRepository implements CustomerRepository {

    @Override
    public Customer create(Customer customer) {
        Session session = JpaUtil.getSession();
        Transaction transaction = session.beginTransaction();

        customer.getAccount().setCustomer(customer);
        session.save(customer.getAccount());

        transaction.commit();
        session.close();
        return customer;
    }

    @Override
    public Customer update(Customer customer, Integer id) {
        Session session = JpaUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Customer customer1 = (Customer) session.get(Customer.class, id);
        Account account = customer1.getAccount();

        account.setName(customer.getAccount().getName());
        account.setStatus(customer.getAccount().getStatus());

        customer1.setSpecialties(customer.getSpecialties());

        session.update(customer1);

        customer = (Customer) session.get(Customer.class, id);

        transaction.commit();
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        Session session = JpaUtil.getSession();
        List<Customer> customers = session.createQuery("from Customer").list();
        customers.forEach(x -> x.getSpecialties().size());

        session.close();
        return customers;
    }

    @Override
    public void delete(Integer id) {
        Session session = JpaUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(session.get(Customer.class, id));

        transaction.commit();
        session.close();
    }
}
