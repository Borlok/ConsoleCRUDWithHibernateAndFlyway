package com.borlok.service;

import com.borlok.model.Account;
import com.borlok.repository.CompositeRepository;
import com.borlok.repository.hibernate.JpaAccountRepository;

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
        return ((JpaAccountRepository) repository.getRepository(new JpaAccountRepository())).create(account);
    }

    @Override
    public List<Account> getAll() {
        return ((JpaAccountRepository) repository.getRepository(new JpaAccountRepository())).getAll();
    }

        @Override
    public Account update(Account account, Integer id) {
        return ((JpaAccountRepository) repository.getRepository(new JpaAccountRepository())).update(account, id);
    }

    @Override
    public void delete(Integer id) {
        repository.getRepository(new JpaAccountRepository()).delete(id);
    }

    @Override
    public String toString() {
        return "AccountService";
    }
}
