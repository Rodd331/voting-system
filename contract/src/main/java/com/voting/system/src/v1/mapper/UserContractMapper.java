package com.voting.system.src.v1.mapper;

import com.voting.system.src.impl.model.UserModel;
import com.voting.system.src.v1.model.request.UserRequest;
import com.voting.system.src.v1.model.response.UserListResponse;
import com.voting.system.src.v1.model.response.UserResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    public static UserListResponse mapUserListToUserListResponse(List<UserModel> userList) {
        List<UserResponse> collect = userList.stream().map(userModel -> UserResponse.builder()
                .idUser(userModel.getIdUser())
                .cpf(userModel.getCpf())
                .build()).collect(Collectors.toList());
        return UserListResponse.builder()
                .userResponseList(collect)
                .size(collect.size())
                .build();
    }

}