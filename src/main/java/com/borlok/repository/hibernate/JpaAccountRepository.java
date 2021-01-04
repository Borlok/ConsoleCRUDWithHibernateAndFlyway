package com.borlok.repository.hibernate;

import com.borlok.model.Account;
import com.borlok.model.Customer;
import com.borlok.repository.AccountRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class JpaAccountRepository implements AccountRepository {
    private SessionFactory sessionFactory;

    public JpaAccountRepository() {
    }

    public JpaAccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Account create(Account account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setAccount(account);
        account.setCustomer(customer);

        int id = (int) session.save(account);

        account = (Account) session.get(Account.class, id);

        transaction.commit();
        session.close();
        return account;
    }

    @Override
    public Account update(Account account, Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Account account1 = (Account) session.get(Account.class, id);

        account1.setName(account.getName());
        account1.setStatus(account.getStatus());
        session.update(account1);

        account = (Account) session.get(Account.class, id);
        transaction.commit();
        session.close();
        return account;
    }

    @Override
    public List<Account> getAll() {
        Session session = sessionFactory.openSession();
        List<Account> accounts = session.createQuery("from Account").list();
        session.close();
        return accounts;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(session.get(Account.class, id));

        transaction.commit();
        session.close();
    }

    @Override
    public String toString() {
        return "JpaAccountRepository{}";
    }
}
