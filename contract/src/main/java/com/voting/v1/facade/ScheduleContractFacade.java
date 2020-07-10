package com.voting.v1.facade;

import com.voting.impl.facade.ScheduleFacade;
import com.voting.v1.model.request.ScheduleRequest;
import com.voting.v1.model.request.VoteRequest;
import com.voting.v1.model.response.ScheduleListResponse;
import com.voting.v1.model.response.ScheduleResponse;
import com.voting.v1.mapper.vote.VoteContractMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.voting.v1.mapper.schadule.ScheduleContractMapper.*;

@Component
@AllArgsConstructor
public class ScheduleContractFacade {

    private final ScheduleFacade scheduleFacade;

    public void vote(VoteRequest vote) {
        scheduleFacade.vote(VoteContractMapper.mapToVoteModel(vote));
    }

    public ScheduleResponse createSchedule(ScheduleRequest schedule) {
        return mapToScheduleResponse(scheduleFacade
                .createSchedule(mapToScheduleModel(schedule)));
    }

    public void deleteByIdSchedule(String idSchedule) {
        scheduleFacade.deleteByIdSchedule(idSchedule);
    }

    public ScheduleListResponse allSchedules() {
        return mapToScheduleListResponse(scheduleFacade.allSchedules());
    }

    public ScheduleListResponse allOpenSchedules() {
        return mapToScheduleListResponse(scheduleFacade.allOpenSchedules());
    }

    public ScheduleResponse findByIdSchedule(String idSchedule) {
        return mapToScheduleResponse(scheduleFacade.findByIdSchedule(idSchedule));
    }

    public void openSchedule(String idSchedule) {
        scheduleFacade.openSchedule(idSchedule);
    }
}