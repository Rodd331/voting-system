package com.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SrcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrcApplication.class, args);
    }
}