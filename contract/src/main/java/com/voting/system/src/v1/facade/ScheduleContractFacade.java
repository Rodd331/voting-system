package com.voting.system.src.v1.facade;

import com.voting.system.src.impl.facade.ScheduleFacade;
import com.voting.system.src.v1.model.request.ScheduleRequest;
import com.voting.system.src.v1.model.response.ScheduleListResponse;
import com.voting.system.src.v1.model.response.ScheduleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.voting.system.src.v1.mapper.ScheduleContractMapper.*;

@Component
@AllArgsConstructor
public class ScheduleContractFacade {

    private ScheduleFacade scheduleFacade;

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