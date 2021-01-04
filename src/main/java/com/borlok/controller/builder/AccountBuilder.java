package com.borlok.controller.builder;

import com.borlok.model.Account;
import com.borlok.model.AccountStatus;

public abstract class AccountBuilder {
    private Account account;

    public void createAccount() {
        account = new Account();
    }

    public abstract void setName(String name);
    public abstract void setStatus(AccountStatus status);

    public Account getAccount() {
        return account;
    }
}
