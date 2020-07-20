package com.voting.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cpf", url = "https://user-info.herokuapp.com/users/")
public interface CpfClient {

    @GetMapping(value = "/{cpf}")
    CpfResponse cpfChecking(@PathVariable String cpf);
}