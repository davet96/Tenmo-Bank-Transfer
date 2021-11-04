package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;


import java.util.List;

public interface TransfersDao {


    public List<Transfers> getTransferHistory(Long transfer_id);
    public List<Transfers> sendBucks(Long account_id);
}
