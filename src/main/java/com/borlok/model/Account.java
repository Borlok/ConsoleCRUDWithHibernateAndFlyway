package com.borlok.model;

public class Account {
    private String name;
    private int id;
    private AccountStatus status;

    public Account() {
        id = 0;
        name = "";
        status = AccountStatus.DELETED;
    }

    public Account(String name, AccountStatus status) {
        this();
        this.name = name;
        this.status = status;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
