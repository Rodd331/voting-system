package com.voting.system.src.v1.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {

    @NotBlank(message = "Field needs to be filled")
    @CPF(message = "Invalid cpf")
    private String cpf;

    @NotBlank(message = "Field needs to be filled")
    @Size(min = 4, max = 8, message = "min 4 max 8 characters")
    private String password;
}