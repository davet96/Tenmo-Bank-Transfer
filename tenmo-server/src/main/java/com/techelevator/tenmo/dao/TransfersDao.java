package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfers;


import java.math.BigDecimal;
import java.util.List;

public interface TransfersDao {


    public List<Transfers> getTransferHistory(Long transferId);
    public void sendBucks(Account accountFrom, Account accountTo, BigDecimal amount);

}
