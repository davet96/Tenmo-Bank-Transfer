package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public interface AccountDao {

    public Account getUserAccount(String username);

    public Account updateAccount(Long account_id);
}
