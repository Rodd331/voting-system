package com.voting.system.src.impl.service;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.handler.ApiException;
import com.voting.system.src.impl.repository.ScheduleRepository;
import com.voting.system.src.impl.utils.Validations;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService implements Validations {

    private ScheduleRepository scheduleRepository;

    public ScheduleEntity createSchedule(ScheduleEntity schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteByIdSchedule(String idSchedule) {
        scheduleRepository.deleteByIdSchedule(idSchedule)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));
    }

    public List<ScheduleEntity> listAllSchedule() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> listAllOpenSchedules() {
        List<ScheduleEntity> lista = scheduleRepository.findAll();

        lista.stream().forEach(list -> {
            if (!list.getStartTimeDate().before(list.getEndTimeDate())) {
                lista.remove(list);
            }
        });
        return lista;
    }

    public ScheduleEntity updateSchedule(ScheduleEntity schedule) {
        return scheduleRepository.save(schedule);
    }

    public ScheduleEntity findByIdSchedule(String idSchedule) {
        return scheduleRepository.findById(idSchedule)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));
    }

    @Override
    public void validatorId(String id) {
        if (scheduleRepository.findById(id).isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Id not found");
        }
    }

    @Override
    public void validationEmptyList() {
        if (scheduleRepository.findAll().isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "There are no registered schedules");
        }
    }
}
