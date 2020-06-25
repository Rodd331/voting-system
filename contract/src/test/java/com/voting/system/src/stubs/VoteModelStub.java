package com.voting.system.src.stubs;

import com.voting.system.src.impl.model.VoteModel;

public class VoteModelStub {
    public static VoteModel generationVoteModel() {
        return VoteModel.builder()
                .cpf("01905793073")
                .password("12345")
                .vote("Sim")
                .idSchedule("someId")
                .build();
    }
}
