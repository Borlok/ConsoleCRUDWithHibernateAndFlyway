package com.borlok.service;

import com.borlok.model.Account;
import com.borlok.repository.AccountRepository;
import com.borlok.repository.CompositeRepository;
import com.borlok.repository.JDBC.JdbcAccountRepository;

import java.util.List;

public class AccountService implements Service<Account> {
    private CompositeRepository repository;

    public AccountService() {
    }

    public AccountService(CompositeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account create(Account account) {
//        return null;
        return ((AccountRepository) repository.getRepository(new JdbcAccountRepository())).create(account);
    }

    @Override
    public List<Account> getAll() {
        return ((AccountRepository) repository.getRepository(new JdbcAccountRepository())).getAll();
    }

        @Override
    public Account update(Account account, Integer id) {
        return ((AccountRepository) repository.getRepository(new JdbcAccountRepository())).update(account, id);
    }

    @Override
    public void delete(Integer id) {
        repository.getRepository(new JdbcAccountRepository()).delete(id);
    }

    @Override
    public String toString() {
        return "AccountService";
    }
}
