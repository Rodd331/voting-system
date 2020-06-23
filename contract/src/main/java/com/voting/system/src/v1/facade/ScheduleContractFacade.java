package com.voting.system.src.v1.facade;

import com.voting.system.src.impl.facade.ScheduleImplFacade;
import com.voting.system.src.v1.model.request.ScheduleRequest;
import com.voting.system.src.v1.model.response.ScheduleListResponse;
import com.voting.system.src.v1.model.response.ScheduleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.voting.system.src.v1.mapper.ScheduleContractMapper.*;

@Component
@AllArgsConstructor
public class ScheduleContractFacade {

    private ScheduleImplFacade scheduleImplFacade;

    public ScheduleResponse createSchedule(ScheduleRequest schedule) {
        return mapScheduleModelToScheduleResponse(scheduleImplFacade.createSchedule(mapScheduleRequestToScheduleModel(schedule)));
    }

    public void deleteByIdSchedule(String idSchedule) {
        scheduleImplFacade.deleteByIdSchedule(idSchedule);
    }

    public ScheduleListResponse allSchedules() {
        return mapScheduleListToScheduleListResponse(scheduleImplFacade.allSchedules());
    }

    public ScheduleListResponse allOpenSchedules() {
        return mapScheduleListToScheduleListResponse(scheduleImplFacade.allOpenSchedules());
    }

    public ScheduleResponse updateSchedule(ScheduleRequest schedule, String idSchedule) {
        return mapScheduleModelToScheduleResponse(scheduleImplFacade.updateSchedule(mapScheduleRequestToScheduleModel(schedule), idSchedule));
    }

    public ScheduleResponse findByIdSchedule(String idSchedule) {
        return mapScheduleModelToScheduleResponse(scheduleImplFacade.findByIdSchedule(idSchedule));
    }
}

