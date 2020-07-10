package com.voting.system.src.v1.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoteRequest {

    @NotBlank(message = "Field needs to be filled")
    String idSchedule;

    @NotBlank(message = "Field needs to be filled")
    String cpf;

    @NotBlank(message = "Field needs to be filled in with Sim/Não")
    String vote;
}