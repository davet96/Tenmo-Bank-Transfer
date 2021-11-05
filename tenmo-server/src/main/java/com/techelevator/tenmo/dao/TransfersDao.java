package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfers;


import java.math.BigDecimal;
import java.util.List;

public interface TransfersDao {

    public void sendBucks(Account accountFrom, Account accountTo, BigDecimal amount);
    public List<Transfers> getAllTransfers(int userId);
    public Transfers getTransferById(int transactionId);

}
