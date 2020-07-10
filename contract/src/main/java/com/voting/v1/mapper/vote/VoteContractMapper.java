package com.voting.v1.mapper.vote;

import com.voting.impl.model.vote.VoteModel;
import com.voting.v1.model.request.VoteRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteContractMapper {

    public static VoteModel mapToVoteModel(VoteRequest vote) {
        return VoteModel.builder()
                .idSchedule(vote.getIdSchedule())
                .cpf(vote.getCpf())
                .vote(vote.getVote())
                .build();
    }
}