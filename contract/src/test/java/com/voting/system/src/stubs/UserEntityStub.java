package com.voting.system.src.stubs;

import com.voting.system.src.impl.entity.UserEntity;

public class UserEntityStub {

    public static UserEntity generationUserEntity() {
        return UserEntity.builder()
                .idUser("someid")
                .cpf("01905793073")
                .password("123456")
                .build();
    }

    public static UserEntity generationUserEntity2() {
        return UserEntity.builder()
                .idUser("someid")
                .cpf("01905793073")
                    .password("123456")
                .build();
    }

}