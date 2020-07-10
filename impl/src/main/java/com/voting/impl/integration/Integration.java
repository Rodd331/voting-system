package com.voting.impl.integration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Integration {

    public static String runConsumer(String cpf){
        RestTemplate restTemplate = new RestTemplate();

        Cpf vote = restTemplate.getForObject(
                "https://user-info.herokuapp.com/users/" + cpf, Cpf.class);

        assert vote != null;
        return vote.getStatus();
    }
}