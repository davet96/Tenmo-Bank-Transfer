package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    public Transfers getTransferHistory(){
        Transfers transfers = null;
             transfers = restTemplate.exchange( baseUrl + "transfers/" + currentUser.getUser().getUsername(), HttpMethod.GET, makeAuthEntity(), Transfers.class).getBody();
            System.out.println("Your transfer history is: " + transfers.getTransfer_id());

                return transfers;
    }

    public void sendBucks(Transfers transfer){
        HttpEntity<Transfers> entity = new HttpEntity<>(transfer, makeAuthEntity().getHeaders() );
        restTemplate.exchange( baseUrl + "transfers/make_transfer/" , HttpMethod.POST,entity, String.class);

    }


}