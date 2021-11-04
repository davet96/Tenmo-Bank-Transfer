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

    @RequestMapping(path = "transfers/{transfer_id}", method = RequestMethod.GET)
    public List<Transfers>  getTransfers(@PathVariable Long transfer_id){
        List<Transfers>  transfers  = dao.getTransferHistory(transfer_id);
    return transfers;}

    @RequestMapping(path = "transfers/make_transfer/", method = RequestMethod.PUT)
    public void sendBucks(@RequestBody Transfers transfer ){
        dao.sendBucks(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());

    }


}
