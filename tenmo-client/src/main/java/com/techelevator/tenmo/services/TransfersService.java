package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class TransfersService {

    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;


    public TransfersService(String url, AuthenticatedUser currentUser){
        this.baseUrl = url;
        this.currentUser = currentUser;
}



    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }




}