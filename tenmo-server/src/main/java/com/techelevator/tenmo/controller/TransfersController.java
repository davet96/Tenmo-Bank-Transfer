package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.TransfersDao;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransfersController {

    private TransfersDao dao;

    public TransfersController(TransfersDao dao){this.dao = dao;}

    @RequestMapping(value = "account/transfers/{id}", method = RequestMethod.GET)
    public List<Transfers> getTransfers(@PathVariable int id) {
        List<Transfers> output = dao.getAllTransfers(id);
        return output;
    }

    @RequestMapping(path = "transfers/make_transfer/", method = RequestMethod.POST)
    public void sendBucks(@RequestBody Transfers transfer ){
        dao.sendBucks(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());

    }

    @RequestMapping(path = "transfers/{id}", method = RequestMethod.GET)
    public Transfers getTransferById(@PathVariable int id) {
        Transfers transfer = dao.getTransferById(id);
        return transfer;
    }


}
