package com.borlok.service;

import com.borlok.model.Account;
import com.borlok.repository.AccountRepository;
import com.borlok.repository.hibernate.JpaAccountRepository;

import java.util.List;

public class AccountService{
    private AccountRepository repository = new JpaAccountRepository();

    public Account create(Account account) {
        return repository.create(account);
    }

    public List<Account> getAll() {
        return repository.getAll();
    }

    public Account getById(int id) {
        return repository.getById(id);
    }

    public Account update(Account account) {
        return repository.update(account);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
