package com.voting.v1.mapper;

import com.voting.v1.model.request.ScheduleRequest;
import com.voting.v1.model.request.VoteRequest;
import com.voting.v1.model.response.ScheduleListResponse;
import com.voting.v1.model.response.ScheduleResponse;
import com.voting.domain.entity.ScheduleEntity;
import com.voting.domain.entity.VoteEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleMapper {

    public static ScheduleEntity mapToScheduleEntity(ScheduleRequest schedule) {
        return ScheduleEntity.builder()
                .nameSchedule(schedule.getNameSchedule())
                .description(schedule.getDescription())
                .scheduleTimeOpenMinut(schedule.getScheduleTimeOpenMinut())
                .build();
    }

    public static ScheduleResponse mapToScheduleResponse(ScheduleEntity schedule) {
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

    public static ScheduleListResponse mapToScheduleListResponse(List<ScheduleEntity> list) {
        List<ScheduleResponse> collect = list.stream().map(scheduleEntity -> ScheduleResponse.builder()
                .idSchedule(scheduleEntity.getIdSchedule())
                .nameSchedule(scheduleEntity.getNameSchedule())
                .description(scheduleEntity.getDescription())
                .votesApproving(scheduleEntity.getVotesApproving())
                .votesNotApproving(scheduleEntity.getVotesNotApproving())
                .startTimeDate(scheduleEntity.getStartTimeDate())
                .scheduleTimeOpenMinut(scheduleEntity.getScheduleTimeOpenMinut())
                .build()).collect(Collectors.toList());
        return ScheduleListResponse.builder()
                .scheduleResponseList(collect)
                .size(collect.size())
                .build();
    }

    public static VoteEntity mapToVoteEntity(VoteRequest vote) {
        return VoteEntity.builder()
                .idSchedule(vote.getIdSchedule())
                .cpf(vote.getCpf())
                .vote(vote.getVote())
                .build();
    }
}