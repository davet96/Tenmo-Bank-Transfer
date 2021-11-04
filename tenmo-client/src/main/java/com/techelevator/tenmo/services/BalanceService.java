package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class BalanceService {


    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;


    public BalanceService(String url, AuthenticatedUser currentUser) {
        this.baseUrl = url;
        this.currentUser = currentUser;
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }

    public Account getBalance() {
        Account account = null;
        try {
            account = restTemplate.exchange(baseUrl + "accounts/user/" + currentUser.getUser().getUsername(), HttpMethod.GET, makeAuthEntity(), Account.class).getBody();
            System.out.println("Your account balance is: $" + account.getBalance());
        } catch (Exception e) {
            System.out.println("Error");
        }
        return account;
    }



}







