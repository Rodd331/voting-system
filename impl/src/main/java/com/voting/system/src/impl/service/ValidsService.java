package com.voting.system.src.impl.service;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.entity.UserEntity;
import com.voting.system.src.impl.handler.ApiException;
import com.voting.system.src.impl.model.VoteModel;
import com.voting.system.src.impl.repository.ScheduleRepository;
import com.voting.system.src.impl.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

import static com.voting.system.src.impl.integration.Integration.consumerCPF;

@Service
@AllArgsConstructor
public class ValidsService {

    private UserRepository userRepository;
    private ScheduleRepository scheduleRepository;

    @Generated
    public void validatorCPF(String cpf) {
        String status = consumerCPF(cpf);
        if (status.equals("U")) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "User not authorized");
        } else if (status != "A") {
            throw new ApiException(HttpStatus.BAD_REQUEST, "External error");
        }
    }

    public void insertVote(VoteModel vote) {
        ScheduleEntity schedule = scheduleRepository.findById(vote.getIdSchedule())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));
        if (vote.getVote().equals("Sim")) {
            schedule.setVotesApproving(schedule.getVotesApproving() + 1);
            schedule.getCpfVoted().add(vote.getCpf());
            scheduleRepository.save(schedule);
        } else if (vote.getVote().equals("Não")) {
            schedule.setVotesNotApproving(schedule.getVotesNotApproving() + 1);
            schedule.getCpfVoted().add(vote.getCpf());
            scheduleRepository.save(schedule);
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Preencha com Sim ou Não");
        }
    }

    public void validatorIdSchedule(VoteModel vote) {
        if (!scheduleRepository.existsById(vote.getIdSchedule())) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Not found");
        }
    }

    public void validatorTimeSchedule(VoteModel vote) {
        ScheduleEntity schedule = scheduleRepository.findById(vote.getIdSchedule())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));

        if (schedule.getStartTimeDate().before(adcMinut(schedule.getStartTimeDate(), schedule.getScheduleTimeOpenMinut()))) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Schedule not open");
        }
    }

    public void validatorPassword(VoteModel vote) {
        UserEntity user = userRepository.findByCpf(vote.getCpf()).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Invalid password"));

        if (!user.getPassword().equals(vote.getPassword())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid password");
        }
    }

    public static Date adcMinut(Date data, Integer minutos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MINUTE, minutos);
        Date dataFinal = cal.getTime();

        return dataFinal;
    }
}
