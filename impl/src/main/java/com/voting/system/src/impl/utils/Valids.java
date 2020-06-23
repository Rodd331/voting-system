package com.voting.system.src.impl.utils;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.entity.UserEntity;
import com.voting.system.src.impl.handler.ApiException;
import com.voting.system.src.impl.model.VoteModel;
import com.voting.system.src.impl.repository.ScheduleRepository;
import com.voting.system.src.impl.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import static com.voting.system.src.impl.integration.Integration.consumerCPF;

@AllArgsConstructor
public class Valids {

    private UserRepository userRepository;
    private ScheduleRepository scheduleRepository;

    public void validatorCPF(String cpf) {
        consumerCPF(cpf);
    }

    public void insertVote(VoteModel vote) {
        ScheduleEntity schedule = scheduleRepository.findById(vote.getIdSchedule())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));
        if (vote.getVote().equals("Sim")) {
            schedule.setVotesApproving(schedule.getVotesApproving() + 1);
            scheduleRepository.save(schedule);
        } else if (vote.getVote().equals("Não")) {
            schedule.setVotesNotApproving(schedule.getVotesNotApproving() + 1);
            scheduleRepository.save(schedule);
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Preencha com Sim ou Não");
        }
    }

    public void validatorId(VoteModel vote) {
        if (!scheduleRepository.existsById(vote.getIdSchedule())) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Not found");
        }
    }

    public void validatorTimeSchedule(VoteModel vote) {
        ScheduleEntity schedule = scheduleRepository.findById(vote.getIdSchedule())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));

        if (schedule.getStartTimeDate().before(schedule.getEndTimeDate())) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Schedule not open");
        }
    }

    public void validatorPassword(VoteModel vote) {
        UserEntity user = userRepository.findByCpf(vote.getCpf()).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Invalid password"));

        if (!user.getPassword().equals(vote.getPassword())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid password");
        }
    }
}
