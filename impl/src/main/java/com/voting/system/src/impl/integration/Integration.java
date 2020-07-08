package com.voting.system.src.impl.integration;

import org.springframework.web.client.RestTemplate;

public class Integration {

    public static String runConsumer(String cpf) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        Vote vote = restTemplate.getForObject(
                "https://user-info.herokuapp.com/users/" + cpf, Vote.class);

        return vote.getStatus();
    }
}