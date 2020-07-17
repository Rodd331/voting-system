package com.voting.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@lombok.Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoteEntity {

    String idSchedule;
    String cpf;
    String vote;
}