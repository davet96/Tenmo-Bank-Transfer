package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.math.BigDecimal;

public class BalanceService {


    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;
    private String authToken = null;
    public void setAuthToken(String authToken){this.authToken = authToken;}
    // create

//    public BalanceService (String url,AuthenticatedUser currentUser) {this.baseUrl = url; this.currentUser = currentUser;}
//    public BigDecimal balance(){BigDecimal balance = new BigDecimal(0);

//    private HttpEntity makeAuthEntity() {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setBearerAuth(currentUser.getToken());
//            HttpEntity entity = new HttpEntity<>(headers);
//            return entity;
//        }
//    try { balance = restTemplate.exchange(baseUrl + "balance/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), BigDecimal.class);
//      balance = balance.getBody();
//
//    }catch (ResourceAccessException e){
//        e.getMessage();
 //   }

        public balance getBalance(BigDecimal usId) {
            Balance balance = 0;
            try {
                ResponseEntity<Reservation> response =
                        restTemplate.exchange(API_BASE_URL + "reservations/" + reservationId,
                                HttpMethod.GET, makeAuthEntity(), Reservation.class);
                reservation = response.getBody();
            } catch (RestClientResponseException | ResourceAccessException e) {
                BasicLogger.log(e.getMessage());
            }
            return reservation;
        }








}
