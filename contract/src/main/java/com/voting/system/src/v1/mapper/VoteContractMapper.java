package com.voting.system.src.v1.mapper;

import com.voting.system.src.impl.model.VoteModel;
import com.voting.system.src.v1.model.request.VoteRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteContractMapper {

    public static VoteModel mapVoteRequestToVoteModel(VoteRequest voteRequest) {
        return VoteModel.builder()
                .cpf(voteRequest.getCpf())
                .password(voteRequest.getPassword())
                .vote(voteRequest.getVote())
                .idSchedule(voteRequest.getIdSchedule())
                .build();
    }
}