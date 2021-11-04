package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    public Account getUserAccount(String username);

    public void deposit(Account account, BigDecimal amount);
    public void withdraw(Account account, BigDecimal amount);
}
