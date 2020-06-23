package com.voting.system.src.impl.facade;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.mapper.ScheduleImplMapper;
import com.voting.system.src.impl.model.ScheduleModel;
import com.voting.system.src.impl.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.voting.system.src.impl.mapper.ScheduleImplMapper.mapScheduleEntityToScheduleModel;
import static com.voting.system.src.impl.mapper.ScheduleImplMapper.mapScheduleModelToScheduleEntity;

@Component
@AllArgsConstructor
public class ScheduleImplFacade {
    private ScheduleService scheduleService;

    public ScheduleModel createSchedule(ScheduleModel schedule) {
        return mapScheduleEntityToScheduleModel(scheduleService.createSchedule(mapScheduleModelToScheduleEntity(schedule)));
    }

    public void deleteByIdSchedule(String idSchedule) {
        scheduleService.validatorId(idSchedule);
        scheduleService.deleteByIdSchedule(idSchedule);
    }

    public List<ScheduleModel> allSchedules() {
        scheduleService.validationEmptyList();
        List<ScheduleEntity> scheduleList = scheduleService.listAllSchedule();
        return scheduleList.stream().map(ScheduleImplMapper::mapScheduleEntityToScheduleModel).collect(Collectors.toList());
    }

    public List<ScheduleModel> allOpenSchedules() {
        scheduleService.validationEmptyList();
        List<ScheduleEntity> scheduleList = scheduleService.listAllOpenSchedules();
        return scheduleList.stream().map(ScheduleImplMapper::mapScheduleEntityToScheduleModel).collect(Collectors.toList());
    }

    public ScheduleModel updateSchedule(ScheduleModel schedule, String idSchedule) {
        scheduleService.validatorId(schedule.getIdSchedule());
        schedule.setIdSchedule(idSchedule);
        return mapScheduleEntityToScheduleModel(scheduleService.updateSchedule(mapScheduleModelToScheduleEntity(schedule)));
    }

    public ScheduleModel findByIdSchedule(String idSchedule) {
        scheduleService.validatorId(idSchedule);
        return mapScheduleEntityToScheduleModel(scheduleService.findByIdSchedule(idSchedule));
    }
}

