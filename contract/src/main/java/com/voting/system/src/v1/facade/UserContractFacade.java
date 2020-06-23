package com.voting.system.src.v1.facade;

import com.voting.system.src.impl.facade.UserImplFacade;
import com.voting.system.src.impl.model.VoteModel;
import com.voting.system.src.v1.mapper.VoteContractMapper;
import com.voting.system.src.v1.model.request.UserRequest;
import com.voting.system.src.v1.model.request.VoteRequest;
import com.voting.system.src.v1.model.response.UserListResponse;
import com.voting.system.src.v1.model.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.voting.system.src.v1.mapper.UserContractMapper.*;
import static com.voting.system.src.v1.mapper.VoteContractMapper.mapVoteRequestToVoteModel;

@Component
@AllArgsConstructor
public class UserContractFacade {

    private UserImplFacade userImplFacade;

    public UserResponse createUser(UserRequest user) {
        return mapUserModelToUserResponse(userImplFacade.createUser(mapUserRequestToUserModel(user)));
    }

    public void deleteUser(String cpf) {
        userImplFacade.deleteUser(cpf);
    }

    public UserListResponse allUsers() {
        return mapUserListToUserListResponse(userImplFacade.allUsers());
    }

    public UserResponse findByCPF(String cpf) {
        return mapUserModelToUserResponse(userImplFacade.findByCPF(cpf));
    }

    public void voteUser(VoteRequest vote) {
        userImplFacade.voteUser(mapVoteRequestToVoteModel(vote));
    }
}