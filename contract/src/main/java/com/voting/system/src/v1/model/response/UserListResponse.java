package com.voting.system.src.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponse {

    private List<UserResponse> userResponseList;
    private int size;
}