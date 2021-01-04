package com.borlok.controller;

import com.borlok.model.Account;
import com.borlok.service.AccountService;
import com.borlok.service.CompositeService;

import java.util.List;

public class AccountController implements Controller<Account>{
    private CompositeService compositeService;

    public AccountController() {
    }

    public AccountController(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @Override
    public Account create(Account account) {
        return ((AccountService) compositeService.getService(new AccountService())).create(account);
    }

    @Override
    public List<Account> getAll() {
        return ((AccountService) compositeService.getService(new AccountService())).getAll();
    }

    @Override
    public Account update(Account account, Integer id) {
        return ((AccountService) compositeService.getService(new AccountService())).update(account, id);
    }

    @Override
    public void delete(Integer id) {
        compositeService.getService(new AccountService()).delete(id);
    }

    @Override
    public String toString() {
        return "AccountController";
    }
}
