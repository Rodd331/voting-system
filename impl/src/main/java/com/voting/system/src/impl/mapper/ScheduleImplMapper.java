package com.voting.system.src.impl.mapper;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.model.ScheduleModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleImplMapper {

    public static ScheduleModel mapScheduleEntityToScheduleModel(ScheduleEntity scheduleEntity) {
        return ScheduleModel.builder()
                .idSchedule(scheduleEntity.getIdSchedule())
                .nameSchedule(scheduleEntity.getNameSchedule())
                .description(scheduleEntity.getDescription())
                .votesApproving(scheduleEntity.getVotesApproving())
                .votesNotApproving(scheduleEntity.getVotesNotApproving())
                .cpfVoted(scheduleEntity.getCpfVoted())
                .startTimeDate(scheduleEntity.getStartTimeDate())
                .endTimeDate(scheduleEntity.getEndTimeDate())
                .build();
    }

    public static ScheduleEntity mapScheduleModelToScheduleEntity(ScheduleModel scheduleModel) {
        return ScheduleEntity.builder()
                .idSchedule(scheduleModel.getIdSchedule())
                .nameSchedule(scheduleModel.getNameSchedule())
                .description(scheduleModel.getDescription())
                .votesApproving(scheduleModel.getVotesApproving())
                .votesNotApproving(scheduleModel.getVotesNotApproving())
                .cpfVoted(scheduleModel.getCpfVoted())
                .startTimeDate(scheduleModel.getStartTimeDate())
                .endTimeDate(scheduleModel.getEndTimeDate())
                .build();
    }
}

