package com.voting.impl.facade;

import com.voting.impl.entity.ScheduleEntity;
import com.voting.impl.mapper.ScheduleMapper;
import com.voting.impl.model.schedule.ScheduleModel;
import com.voting.impl.model.vote.VoteModel;
import com.voting.impl.service.ScheduleService;
import com.voting.impl.service.ValidatonsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ScheduleFacade {

    private final ScheduleService scheduleService;
    private final ValidatonsService validatonsService;

    public void vote(VoteModel vote) {
        validatonsService.checkCpfAlreadyVoted(vote);
        validatonsService.validatorTimeSchedule(vote.getIdSchedule());
        validatonsService.validatorId(vote.getIdSchedule());
        validatonsService.validatorCPF(vote.getCpf());
        scheduleService.vote(vote);
    }

    public ScheduleModel createSchedule(ScheduleModel schedule) {
        return ScheduleMapper.mapToScheduleModel(scheduleService.createSchedule(ScheduleMapper.mapToScheduleEntity(schedule)));
    }

    public void deleteByIdSchedule(String idSchedule) {
        validatonsService.validatorId(idSchedule);
        scheduleService.deleteByIdSchedule(idSchedule);
    }

    public List<ScheduleModel> allSchedules() {
        validatonsService.validationEmptyList();
        List<ScheduleEntity> scheduleList = scheduleService.listAllSchedule();
        return scheduleList.stream().map(ScheduleMapper::mapToScheduleModel).collect(Collectors.toList());
    }

    public List<ScheduleModel> allOpenSchedules() {
        validatonsService.validationOpenSchedules();
        List<ScheduleEntity> scheduleList = scheduleService.listAllOpenSchedules();
        return scheduleList.stream().map(ScheduleMapper::mapToScheduleModel).collect(Collectors.toList());
    }

    public ScheduleModel findByIdSchedule(String idSchedule) {
        validatonsService.validatorId(idSchedule);
        return ScheduleMapper.mapToScheduleModel(scheduleService.findByIdSchedule(idSchedule));
    }

    public void openSchedule(String idSchedule) {
        validatonsService.validatorId(idSchedule);
        scheduleService.openSchedule(idSchedule);
    }
}