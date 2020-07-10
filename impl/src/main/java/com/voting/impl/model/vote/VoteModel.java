package com.voting.impl.model.vote;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@lombok.Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoteModel {

    String idSchedule;
    String cpf;
    String vote;
}