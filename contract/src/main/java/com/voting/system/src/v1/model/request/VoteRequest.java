package com.voting.system.src.v1.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteRequest {

    @NotBlank(message = "Field needs to be filled")
    @CPF(message = "Invalid cpf")
    private String cpf;

    @NotBlank(message = "Field needs to be filled")
    @Size(min = 4, max = 8, message = "min 4 max 8 characters")
    private String password;

    @NotBlank(message = "Field needs to be filled")
    @Size(min = 3, max = 3, message = "Preencha seu voto com Sim ou Não")
    private String vote;

    @NotBlank(message = "Field needs to be filled")
    private String idSchedule;
}