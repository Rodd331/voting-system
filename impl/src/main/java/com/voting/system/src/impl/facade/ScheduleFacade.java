package com.voting.system.src.impl.facade;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.mapper.ScheduleMapper;
import com.voting.system.src.impl.model.schedule.ScheduleModel;
import com.voting.system.src.impl.model.vote.VoteModel;
import com.voting.system.src.impl.service.ScheduleService;
import com.voting.system.src.impl.service.ValidatonsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.voting.system.src.impl.mapper.ScheduleMapper.mapToScheduleEntity;
import static com.voting.system.src.impl.mapper.ScheduleMapper.mapToScheduleModel;

@Component
@AllArgsConstructor
public class ScheduleFacade {

    private ScheduleService scheduleService;
    private ValidatonsService validatonsService;

    public void vote(VoteModel vote) {
        validatonsService.checkCpfAlreadyVoted(vote);
        validatonsService.validatorTimeSchedule(vote.getIdSchedule());
        validatonsService.validatorId(vote.getIdSchedule());
        validatonsService.validatorCPF(vote.getCpf());
        scheduleService.vote(vote);
    }

    public ScheduleModel createSchedule(ScheduleModel schedule) {
        return mapToScheduleModel(scheduleService.createSchedule(mapToScheduleEntity(schedule)));
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
        return mapToScheduleModel(scheduleService.findByIdSchedule(idSchedule));
    }

    public void openSchedule(String idSchedule) {
        validatonsService.validatorId(idSchedule);
        scheduleService.openSchedule(idSchedule);
    }
}