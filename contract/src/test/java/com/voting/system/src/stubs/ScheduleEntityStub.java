package com.voting.system.src.stubs;

import com.voting.system.src.impl.entity.ScheduleEntity;

import java.util.Collections;
import java.util.Date;

public class ScheduleEntityStub {

    public static ScheduleEntity generationScheduleEntity() {
        return ScheduleEntity.builder()
                .idSchedule("someid")
                .nameSchedule("Teste01")
                .description("Testando metodo")
                .votesApproving(3)
                .votesNotApproving(2)
                .cpfVoted(Collections.emptyList())
                .startTimeDate(new Date())
                .build();
    }
}