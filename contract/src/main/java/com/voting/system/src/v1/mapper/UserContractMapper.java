package com.voting.system.src.v1.mapper;

import com.voting.system.src.impl.model.UserModel;
import com.voting.system.src.v1.model.request.UserRequest;
import com.voting.system.src.v1.model.response.UserResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserContractMapper {

    public static UserResponse mapUserModelToUserResponse(UserModel userModel) {
        return UserResponse.builder()
                .idUser(userModel.getIdUser())
                .cpf(userModel.getCpf())
                .build();
    }

    public static UserModel mapUserRequestToUserModel(UserRequest userRequest) {
        return UserModel.builder()
                .cpf(userRequest.getCpf())
                .password(userRequest.getPassword())
                .build();
    }

}
