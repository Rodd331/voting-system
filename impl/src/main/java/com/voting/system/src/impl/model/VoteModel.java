package com.voting.system.src.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@lombok.Data
@Builder
public class VoteModel {

    private String cpf;
    private String password;
    private String vote;
    private String idSchedule;
}