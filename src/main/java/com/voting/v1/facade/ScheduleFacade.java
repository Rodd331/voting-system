package com.voting.v1.facade;

import com.voting.v1.model.request.ScheduleRequest;
import com.voting.v1.model.request.VoteRequest;
import com.voting.v1.model.response.ScheduleListResponse;
import com.voting.v1.model.response.ScheduleResponse;
import com.voting.v1.service.ScheduleService;
import com.voting.v1.service.ValidatonsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.voting.v1.mapper.ScheduleMapper.*;

@Component
@AllArgsConstructor
public class ScheduleFacade {

    private final ScheduleService scheduleService;
    private final ValidatonsService validatonsService;

    public void vote(VoteRequest vote) {
        validatonsService.checkCpfAlreadyVoted(mapToVoteEntity(vote));
        validatonsService.validatorTimeSchedule(vote.getIdSchedule());
        validatonsService.validatorId(vote.getIdSchedule());
        validatonsService.validatorCPF(vote.getCpf());
        scheduleService.vote(mapToVoteEntity(vote));
    }

    public ScheduleResponse createSchedule(ScheduleRequest schedule) {
        return mapToScheduleResponse(scheduleService.createSchedule(mapToScheduleEntity(schedule)));
    }

    public void deleteByIdSchedule(String idSchedule) {
        validatonsService.validatorId(idSchedule);
        scheduleService.deleteByIdSchedule(idSchedule);
    }

    public ScheduleListResponse allSchedules() {
        validatonsService.validationEmptyList();
        return mapToScheduleListResponse(scheduleService.listAllSchedule());
    }

    public ScheduleListResponse allOpenSchedules() {
        validatonsService.validationOpenSchedules();
        return mapToScheduleListResponse(scheduleService.listAllOpenSchedules());
    }

    public ScheduleResponse findByIdSchedule(String idSchedule) {
        validatonsService.validatorId(idSchedule);
        return mapToScheduleResponse(scheduleService.findByIdSchedule(idSchedule));
    }

    public void openSchedule(String idSchedule) {
        validatonsService.validatorId(idSchedule);
        scheduleService.openSchedule(idSchedule);
    }
}