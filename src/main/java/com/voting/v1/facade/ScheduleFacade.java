package com.voting.v1.facade;

import com.voting.v1.dto.schedule.ScheduleListResponse;
import com.voting.v1.dto.schedule.ScheduleRequest;
import com.voting.v1.dto.schedule.ScheduleResponse;
import com.voting.v1.dto.schedule.ScheduleResult;
import com.voting.v1.dto.vote.VoteRequest;
import com.voting.v1.service.KafkaService;
import com.voting.v1.service.ScheduleService;
import com.voting.v1.service.ValidatonsService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.voting.v1.mapper.ScheduleMapper.*;

@Component
@AllArgsConstructor
@EnableScheduling
public class ScheduleFacade {

    private final ScheduleService scheduleService;
    private final ValidatonsService validatonsService;
    private final KafkaService kafkaService;
    private final String close = "close";
    private final String open = "open";

    public void vote(VoteRequest vote) {
        validatonsService.checkCpfAlreadyVoted(mapToVoteEntity(vote));
        validatonsService.validatorTimeSchedule(vote.getIdSchedule());
        validatonsService.validatorId(vote.getIdSchedule());
        validatonsService.validatorCPF(vote.getCpf());
        scheduleService.vote(mapToVoteEntity(vote));
    }

    public ScheduleResponse createSchedule(ScheduleRequest schedule) {
        return mapToScheduleResponse(scheduleService.createSchedule(mapToScheduleEntity(schedule)));
    }

    public void deleteByIdSchedule(String idSchedule) {
        validatonsService.validatorId(idSchedule);
        scheduleService.deleteByIdSchedule(idSchedule);
    }

    public ScheduleListResponse allSchedules() {
        validatonsService.validationEmptyList();
        return mapToScheduleListResponse(scheduleService.listAllSchedule());
    }

    public ScheduleListResponse allOpenSchedules() {
        validatonsService.validationCostumerSchedules();
        return mapToScheduleListResponse(scheduleService.listCostumerAllSchedules(open));
    }

    public ScheduleResponse findByIdSchedule(String idSchedule) {
        validatonsService.validatorId(idSchedule);
        return mapToScheduleResponse(scheduleService.findByIdSchedule(idSchedule));
    }

    @Scheduled(fixedDelay = 1000)
    public void sendResults() {
        scheduleService.scheduleResults()
            .stream()
            .filter(result ->
                Objects.equals(scheduleService.findByIdSchedule(result.getIdSchedule())
                    .getMessageAlreadySent(), "N"))
            .map((ScheduleResult scheduleResult) -> kafkaService.makeRecord(scheduleResult))
            .forEach(kafkaService::send);
        scheduleService.listCostumerAllSchedules(close)
            .forEach(scheduleService::setMessageAlreadySent);
    }

    public void openSchedule(String idSchedule) {
        validatonsService.validatorId(idSchedule);
        scheduleService.openSchedule(idSchedule);
    }
}