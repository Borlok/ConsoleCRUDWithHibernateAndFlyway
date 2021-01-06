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

    public Account update(Account account, Integer id) {
        return accountService.update(account, id);
    }

    public void delete(Integer id) {
        accountService.delete(id);
    }
}
