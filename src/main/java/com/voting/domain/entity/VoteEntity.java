package com.voting.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoteEntity {

    String idSchedule;
    String cpf;
    String vote;
}