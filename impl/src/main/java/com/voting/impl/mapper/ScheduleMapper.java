package com.voting.impl.mapper;

import com.voting.impl.model.schedule.ScheduleModel;
import com.voting.impl.entity.ScheduleEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleMapper {

    public static ScheduleModel mapToScheduleModel(ScheduleEntity scheduleEntity) {
        return ScheduleModel.builder()
                .idSchedule(scheduleEntity.getIdSchedule())
                .nameSchedule(scheduleEntity.getNameSchedule())
                .description(scheduleEntity.getDescription())
                .votesApproving(scheduleEntity.getVotesApproving())
                .votesNotApproving(scheduleEntity.getVotesNotApproving())
                .cpfVoted(scheduleEntity.getCpfVoted())
                .startTimeDate(scheduleEntity.getStartTimeDate())
                .scheduleTimeOpenMinut(scheduleEntity.getScheduleTimeOpenMinut())
                .build();
    }

    public static ScheduleEntity mapToScheduleEntity(ScheduleModel scheduleModel) {
        return ScheduleEntity.builder()
                .idSchedule(scheduleModel.getIdSchedule())
                .nameSchedule(scheduleModel.getNameSchedule())
                .description(scheduleModel.getDescription())
                .votesApproving(0)
                .votesNotApproving(0)
                .cpfVoted(new ArrayList<>())
                .startTimeDate(scheduleModel.getStartTimeDate())
                .scheduleTimeOpenMinut(scheduleModel.getScheduleTimeOpenMinut())
                .build();
    }
}