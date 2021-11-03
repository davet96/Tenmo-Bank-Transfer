package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    private long account_id;
    private long user_id;
    private BigDecimal balance;
    private String username;

    public Account() {
    }

    public Account(long accountId, long userId, BigDecimal balance, String username) {
        this.account_id = accountId;
        this.user_id = userId;
        this.balance = balance;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
