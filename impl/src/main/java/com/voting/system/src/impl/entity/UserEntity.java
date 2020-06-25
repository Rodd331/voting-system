package com.voting.system.src.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@lombok.Data
@Builder
@Document(collection = "Users")
public class UserEntity {

    @Id
    String idUser;

    @Indexed(unique = true)
    String cpf;
    String password;
}