package com.voting.system.src.impl.mapper;

import com.voting.system.src.impl.entity.UserEntity;
import com.voting.system.src.impl.model.UserModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserImplMapper {

    public static UserModel mapUserEntityToUserModel(UserEntity userEntity) {
        return UserModel.builder()
                .idUser(userEntity.getIdUser())
                .cpf(userEntity.getCpf())
                .build();
    }

    public static UserEntity mapUserModelToUserEntity(UserModel userModel) {
        return UserEntity.builder()
                .idUser(userModel.getIdUser())
                .cpf(userModel.getCpf())
                .password(userModel.getPassword())
                .build();
    }
}