package com.voting.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class VoteEntity {

    private String idSchedule;
    private String cpf;
    private String vote;
}