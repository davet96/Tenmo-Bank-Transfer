package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.TransfersDao;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransfersController {

    private TransfersDao dao;

    public TransfersController(TransfersDao dao){this.dao = dao;}

    @RequestMapping(path = "transfers/{transfer_id}", method = RequestMethod.GET)
    public List<Transfers>  getTransfers(@PathVariable Long transfer_id){
        List<Transfers>  transfers  = dao.getTransferHistory(transfer_id);
    return transfers;}

    @RequestMapping(path = "transfers/{account_id}", method = RequestMethod.PUT)
    public List<Transfers> sendBucks(@PathVariable Long account_id){
        List<Transfers> sendBucks = dao.sendBucks(account_id);
        return sendBucks;
    }


}
