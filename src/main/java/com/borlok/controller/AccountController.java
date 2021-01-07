package com.borlok.controller;

import com.borlok.model.Account;
import com.borlok.service.AccountService;

import java.util.List;

public class AccountController{
    private AccountService accountService = new AccountService();

    public Account create(Account account) {
        return accountService.create(account);
    }

    public List<Account> getAll() {
        return accountService.getAll();
    }

    public Account getById(int id) {
        return accountService.getById(id);
    }

    public Account update(Account account) {
        return accountService.update(account);
    }

    public void delete(Integer id) {
        accountService.delete(id);
    }
}
