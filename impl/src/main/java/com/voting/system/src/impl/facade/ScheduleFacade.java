package com.voting.system.src.impl.facade;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.mapper.ScheduleMapper;
import com.voting.system.src.impl.model.ScheduleModel;
import com.voting.system.src.impl.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.voting.system.src.impl.mapper.ScheduleMapper.mapToScheduleModel;
import static com.voting.system.src.impl.mapper.ScheduleMapper.mapToScheduleEntity;

@Component
@AllArgsConstructor
public class ScheduleFacade {

    private ScheduleService scheduleService;

    public ScheduleModel createSchedule(ScheduleModel schedule) {
        return mapToScheduleModel(scheduleService.createSchedule(mapToScheduleEntity(schedule)));
    }

    public void deleteByIdSchedule(String idSchedule) {
        scheduleService.validatorId(idSchedule);
        scheduleService.deleteByIdSchedule(idSchedule);
    }

    public List<ScheduleModel> allSchedules() {
        scheduleService.validationEmptyList();
        List<ScheduleEntity> scheduleList = scheduleService.listAllSchedule();
        return scheduleList.stream().map(ScheduleMapper::mapToScheduleModel).collect(Collectors.toList());
    }

    public List<ScheduleModel> allOpenSchedules() {
        scheduleService.validationEmptyList();
        List<ScheduleEntity> scheduleList = scheduleService.listAllOpenSchedules();
        return scheduleList.stream().map(ScheduleMapper::mapToScheduleModel).collect(Collectors.toList());
    }

    public ScheduleModel findByIdSchedule(String idSchedule) {
        scheduleService.validatorId(idSchedule);
        return mapToScheduleModel(scheduleService.findByIdSchedule(idSchedule));
    }

    public void openSchedule(String idSchedule) {
        scheduleService.openSchedule(idSchedule);
    }
}