package com.voting.system.src.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String idUser;
    private String cpf;
}