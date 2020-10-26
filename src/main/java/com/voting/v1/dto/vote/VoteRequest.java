package com.voting.v1.dto.vote;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteRequest {

    @NotBlank(message = "Field needs to be filled")
    private String idSchedule;

    @CPF
    @NotBlank(message = "Field needs to be filled")
    private String cpf;

    @NotBlank(message = "Field needs to be filled in with Sim/Não")
    private String vote;
}