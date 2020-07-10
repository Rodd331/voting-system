package com.voting.system.src.impl.service;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.handler.ApiException;
import com.voting.system.src.impl.model.vote.VoteModel;
import com.voting.system.src.impl.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.voting.system.src.impl.Utils.Utils.adcMinut;

@Service
@AllArgsConstructor
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public void vote(VoteModel vote) {
        ScheduleEntity schedule = scheduleRepository.findById(vote.getIdSchedule())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));

        processVote(schedule, vote);
        addToVoters(schedule, vote);
        scheduleRepository.save(schedule);
    }

    public void addToVoters(ScheduleEntity schedule, VoteModel vote) {
        schedule.getCpfVoted().add(vote.getCpf());
    }

    public void processVote(ScheduleEntity schedule, VoteModel vote) {
        if (vote.getVote().equals("Sim")) {
            schedule.setVotesApproving(schedule.getVotesApproving() + 1);
        } else if (vote.getVote().equals("Não")) {
            schedule.setVotesNotApproving(schedule.getVotesNotApproving() + 1);
        } else {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Field needs to be filled in with Sim/Não");
        }
    }

    public ScheduleEntity createSchedule(ScheduleEntity schedule) {
        if (schedule.getScheduleTimeOpenMinut() == 0) {
            schedule.setScheduleTimeOpenMinut(1);
        }
        return scheduleRepository.save(schedule);
    }

    public void deleteByIdSchedule(String idSchedule) {
        scheduleRepository.deleteByIdSchedule(idSchedule);
    }

    public List<ScheduleEntity> listAllSchedule() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> listAllOpenSchedules() {
        List<ScheduleEntity> lista = scheduleRepository.findAllByStartTimeDateIsNotNull();
        List<ScheduleEntity> listaResult = new ArrayList<>();
        Date dateSystem = new Date();

        try {
            lista.stream().forEach(list -> {
                Date dateCompare = adcMinut(list.getStartTimeDate(), list.getScheduleTimeOpenMinut());
                if (dateSystem.before(dateCompare)) {
                    listaResult.add(list);
                }
            });
            return listaResult;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Open schedule not found");
        }
    }

    public ScheduleEntity findByIdSchedule(String idSchedule) {
        return scheduleRepository.findById(idSchedule)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));
    }

    public void openSchedule(String idSchedule) {
        ScheduleEntity schedule = scheduleRepository.findByIdSchedule(idSchedule);
        if (schedule.getStartTimeDate() != null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Agenda has already been voted");
        } else {
            schedule.setStartTimeDate(adcMinut(new Date(), 0));
            scheduleRepository.save(schedule);
        }
    }
}