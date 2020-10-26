package com.voting.v1.service;

import com.voting.domain.entity.ScheduleEntity;
import com.voting.domain.entity.VoteEntity;
import com.voting.domain.repository.ScheduleRepository;
import com.voting.exception.ApiException;
import com.voting.v1.dto.schedule.ScheduleResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.voting.v1.mapper.ScheduleMapper.mapToScheduleResult;
import static com.voting.v1.service.utils.Utils.adcMinut;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public void vote(VoteEntity vote) {
        ScheduleEntity schedule = scheduleRepository.findByIdSchedule(vote.getIdSchedule());

        processVote(schedule, vote);
        addToVoters(schedule, vote);
        scheduleRepository.save(schedule);
    }

    private void addToVoters(ScheduleEntity schedule, VoteEntity vote) {
        schedule.getCpfVoted().add(vote.getCpf());
    }

    private void processVote(ScheduleEntity schedule, VoteEntity vote) {
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
        schedule.setCpfVoted(new ArrayList<>());
        schedule.setVotesApproving(0);
        schedule.setVotesNotApproving(0);
        return scheduleRepository.save(schedule);
    }

    public void deleteByIdSchedule(String idSchedule) {
        scheduleRepository.deleteByIdSchedule(idSchedule);
    }

    public List<ScheduleEntity> listAllSchedule() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> listCostumerAllSchedules(String costumer) {
        List<ScheduleEntity> lista = scheduleRepository.findAllByStartTimeDateIsNotNull();
        List<ScheduleEntity> listaResult = new ArrayList<>();
        Date dateSystem = new Date();

        lista.forEach(list -> {
            Date dateCompare = adcMinut(list.getStartTimeDate(), list.getScheduleTimeOpenMinut());
            if (costumer.equals("close")) {
                if (dateSystem.after(dateCompare)) {
                    listaResult.add(list);
                }
            } else if (costumer.equals("open")) {
                if (dateSystem.before(dateCompare)) {
                    listaResult.add(list);
                }
            }
        });
        return listaResult;
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

    public void setMessageAlreadySent(ScheduleEntity scheduleEntity) {
        scheduleEntity.setMessageAlreadySent("S");
        scheduleRepository.save(scheduleEntity);
    }

    public List<ScheduleResult> scheduleResults() {
        List<ScheduleEntity> lista = scheduleRepository.findAllByStartTimeDateIsNotNull();
        List<ScheduleResult> listaResult = new ArrayList<>();
        Date dateSystem = new Date();

        lista.forEach(list -> {
            Date dateCompare = adcMinut(list.getStartTimeDate(), list.getScheduleTimeOpenMinut());
                if (dateSystem.after(dateCompare)) {
                    listaResult.add(mapToScheduleResult(list));
                }
        });
        return listaResult;
    }

    public static String scheduleResultFinal(ScheduleEntity schedule) {
        if(schedule.getVotesApproving() > schedule.getVotesNotApproving()){
            return "Aprovada";
        } else if(schedule.getVotesApproving() < schedule.getVotesNotApproving()){
            return "Reprovada";
        } else{
            return "Empate";
        }
    }
}