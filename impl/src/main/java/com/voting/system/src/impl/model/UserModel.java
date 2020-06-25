package com.voting.system.src.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@lombok.Data
@Builder
public class UserModel {

    private String idUser;
    private String cpf;
    private String password;
}