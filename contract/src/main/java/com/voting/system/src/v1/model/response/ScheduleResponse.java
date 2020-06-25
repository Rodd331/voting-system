package com.voting.system.src.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {

    String idSchedule;
    String nameSchedule;
    String description;

    Integer votesApproving;
    Integer votesNotApproving;

    Date startTimeDate;

    int scheduleTimeOpenMinut;
}