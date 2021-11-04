package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {

    private AccountDao dao;

    public AccountController(AccountDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "accounts/user/{username}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable String username){
        return dao.getUserAccount(username);
    }

    @RequestMapping(path = "accounts/users", method = RequestMethod.GET)
    public List<User> viewUser(Long userId){ return dao.viewUserList(userId);}

    @RequestMapping(path = "accounts", method = RequestMethod.GET)
    public List<Account> accountsWithUsername(){return dao.getAccountWithUsername();}
}
