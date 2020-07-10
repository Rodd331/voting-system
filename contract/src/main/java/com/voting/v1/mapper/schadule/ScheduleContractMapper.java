package com.voting.v1.mapper.schadule;

import com.voting.impl.model.schedule.ScheduleModel;
import com.voting.v1.model.request.ScheduleRequest;
import com.voting.v1.model.response.ScheduleListResponse;
import com.voting.v1.model.response.ScheduleResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleContractMapper {

    public static ScheduleModel mapToScheduleModel(ScheduleRequest schedule) {
        return ScheduleModel.builder()
                .nameSchedule(schedule.getNameSchedule())
                .description(schedule.getDescription())
                .scheduleTimeOpenMinut(schedule.getScheduleTimeOpenMinut())
                .build();
    }

    public static ScheduleResponse mapToScheduleResponse(ScheduleModel schedule) {
        return ScheduleResponse.builder()
                .idSchedule(schedule.getIdSchedule())
                .nameSchedule(schedule.getNameSchedule())
                .description(schedule.getDescription())
                .votesApproving(schedule.getVotesApproving())
                .votesNotApproving(schedule.getVotesNotApproving())
                .startTimeDate(schedule.getStartTimeDate())
                .scheduleTimeOpenMinut(schedule.getScheduleTimeOpenMinut())
                .build();
    }

    public static ScheduleListResponse mapToScheduleListResponse(List<ScheduleModel> list) {
        List<ScheduleResponse> collect = list.stream().map(scheduleModel -> ScheduleResponse.builder()
                .idSchedule(scheduleModel.getIdSchedule())
                .nameSchedule(scheduleModel.getNameSchedule())
                .description(scheduleModel.getDescription())
                .votesApproving(scheduleModel.getVotesApproving())
                .votesNotApproving(scheduleModel.getVotesNotApproving())
                .startTimeDate(scheduleModel.getStartTimeDate())
                .scheduleTimeOpenMinut(scheduleModel.getScheduleTimeOpenMinut())
                .build()).collect(Collectors.toList());
        return ScheduleListResponse.builder()
                .scheduleResponseList(collect)
                .size(collect.size())
                .build();
    }
}