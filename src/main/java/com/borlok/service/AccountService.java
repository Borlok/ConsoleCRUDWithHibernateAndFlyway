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

    public Account update(Account account, Integer id) {
        return repository.update(account, id);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
