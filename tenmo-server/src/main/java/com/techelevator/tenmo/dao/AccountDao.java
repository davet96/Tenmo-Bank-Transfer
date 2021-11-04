package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    public Account getUserAccount(String username);

    public void deposit(Account account, BigDecimal amount);
    public void withdraw(Account account, BigDecimal amount);
    public List<User> viewUserList(Long userId);
    public List<Account> getAccountWithUsername();

}
