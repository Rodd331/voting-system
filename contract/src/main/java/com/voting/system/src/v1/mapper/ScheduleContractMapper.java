package com.voting.system.src.v1.mapper;

import com.voting.system.src.impl.model.ScheduleModel;
import com.voting.system.src.v1.model.request.ScheduleRequest;
import com.voting.system.src.v1.model.response.ScheduleListResponse;
import com.voting.system.src.v1.model.response.ScheduleResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleContractMapper {

    public static ScheduleModel mapScheduleRequestToScheduleModel(ScheduleRequest schedule) {
        return ScheduleModel.builder()
                .idSchedule(schedule.getIdSchedule())
                .nameSchedule(schedule.getNameSchedule())
                .description(schedule.getDescription())
                .votesApproving(schedule.getVotesApproving())
                .votesNotApproving(schedule.getVotesNotApproving())
                .cpfVoted(schedule.getCpfVoted())
                .startTimeDate(schedule.getStartTimeDate())
                .endTimeDate(schedule.getEndTimeDate())
                .dateCreationSchedule(schedule.getDateCreationSchedule())
                .build();
    }

    public static ScheduleResponse mapScheduleModelToScheduleResponse(ScheduleModel schedule) {
        return ScheduleResponse.builder()
                .idSchedule(schedule.getIdSchedule())
                .nameSchedule(schedule.getNameSchedule())
                .description(schedule.getDescription())
                .votesApproving(schedule.getVotesApproving())
                .votesNotApproving(schedule.getVotesNotApproving())
                .startTimeDate(schedule.getStartTimeDate())
                .endTimeDate(schedule.getEndTimeDate())
                .dateCreationSchedule(schedule.getDateCreationSchedule())
                .build();
    }

    public static ScheduleListResponse mapScheduleListToScheduleListResponse(List<ScheduleModel> ScheduleList) {
        List<ScheduleResponse> collect = ScheduleList.stream().map(scheduleModel -> ScheduleResponse.builder()
                .idSchedule(scheduleModel.getIdSchedule())
                .nameSchedule(scheduleModel.getNameSchedule())
                .description(scheduleModel.getDescription())
                .votesApproving(scheduleModel.getVotesApproving())
                .votesNotApproving(scheduleModel.getVotesNotApproving())
                .startTimeDate(scheduleModel.getStartTimeDate())
                .endTimeDate(scheduleModel.getEndTimeDate())
                .dateCreationSchedule(scheduleModel.getDateCreationSchedule())
                .build()).collect(Collectors.toList());
        return ScheduleListResponse.builder()
                .scheduleResponseList(collect)
                .size(collect.size())
                .build();
    }
}

