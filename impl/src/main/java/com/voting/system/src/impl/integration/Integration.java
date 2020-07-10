package com.voting.system.src.impl.integration;

import org.springframework.web.client.RestTemplate;

public class Integration {

    public static String runConsumer(String cpf){
        RestTemplate restTemplate = new RestTemplate();

        Cpf vote = restTemplate.getForObject(
                "https://user-info.herokuapp.com/users/" + cpf, Cpf.class);

        return vote.getStatus();
    }
}