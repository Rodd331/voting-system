package com.voting.system.src.impl.service;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.handler.ApiException;
import com.voting.system.src.impl.model.vote.VoteModel;
import com.voting.system.src.impl.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.voting.system.src.impl.Utils.Utils.adcMinut;
import static com.voting.system.src.impl.integration.Integration.runConsumer;

@Service
@AllArgsConstructor
public class ValidatonsService {

    private ScheduleRepository scheduleRepository;

    public void validatorCPF(String cpf) {
        String consume = runConsumer("01905793073");

        if (consume.startsWith("UNAB")) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Unauthorized user to vote");
        } else if (!consume.startsWith("ABLE")) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "External error");
        }
    }

    public void validatorId(String id) {
        if (scheduleRepository.findById(id).isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Id not found");
        }
    }

    public void validationEmptyList() {
        if (scheduleRepository.findAll().isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "There are no registered schedules");
        }
    }

    public void validationOpenSchedules() {
        if (scheduleRepository.findAllByStartTimeDateIsNotNull().isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "There are no Open schedules");
        }
    }

    public void validatorTimeSchedule(String idSchedule) {
        ScheduleEntity schedule = scheduleRepository.findById(idSchedule)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));

        Date system = new Date();
        schedule.setStartTimeDate(adcMinut(schedule.getStartTimeDate(), 180));
        Date compare = adcMinut(schedule.getStartTimeDate(), schedule.getScheduleTimeOpenMinut());

        if (system.after(compare)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Schedule not open");
        }
    }

    public void checkCpfAlreadyVoted(VoteModel vote) {
        ScheduleEntity schedule = scheduleRepository.findByIdSchedule(vote.getIdSchedule());

        if (schedule.getCpfVoted().contains(vote.getCpf())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Cpf already voted");
        }
    }
}