package com.voting.impl.service;

import com.voting.impl.entity.ScheduleEntity;
import com.voting.impl.handler.ApiException;
import com.voting.impl.model.vote.VoteModel;
import com.voting.impl.repository.ScheduleRepository;
import com.voting.impl.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

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
            lista.forEach(list -> {
                Date dateCompare = Utils.adcMinut(list.getStartTimeDate(), list.getScheduleTimeOpenMinut());
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
            schedule.setStartTimeDate(Utils.adcMinut(new Date(), 0));
            scheduleRepository.save(schedule);
        }
    }
}