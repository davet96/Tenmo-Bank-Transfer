package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.Scanner;

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

    public Transfers[] transfersList() {
        Transfers [] transfers = null;
        try {
            transfers = restTemplate.exchange(baseUrl + "account/transfers/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), Transfers[].class).getBody();

            System.out.println("***********Transfers****************");
            for (Transfers trans : transfers) {

                System.out.println("Account number " + trans.getAccount_from() + " transferred money to: ");
                System.out.println("Account number " + trans.getAccount_to());
                System.out.println("With transfer Id of: " + trans.getTransferId());
                System.out.println("For the amount of: $" + trans.getAmount());
                System.out.println();
            }
            System.out.println("Enter transfer ID to view details: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Integer.parseInt(input) != 0) {
                boolean hasTransferId = false;
                for (Transfers trans : transfers) {
                    if (Integer.parseInt(input) == trans.getTransferId()) {
                        Transfers transId = restTemplate.exchange(baseUrl + "transfers/" + trans.getTransferId(), HttpMethod.GET, makeAuthEntity(), Transfers.class).getBody();
                        hasTransferId = true;
                        System.out.println("Transfers By ID");
                        System.out.println("ID: " + transId.getTransferId());
                        System.out.println("Amount Transferred: $" + transId.getAmount());
                        System.out.println();
                    }
                }
                if (!hasTransferId) {
                    System.out.println("Incorrect Transfer ID");
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong in Print Transfers");
        }
        return transfers;
    }


    public void sendBucks(Transfers transfer){
        HttpEntity<Transfers> entity = new HttpEntity<>(transfer, makeAuthEntity().getHeaders() );
        restTemplate.exchange( baseUrl + "transfers/make_transfer/" , HttpMethod.POST,entity, String.class);

    }



}