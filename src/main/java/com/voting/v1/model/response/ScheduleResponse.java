package com.voting.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {

    private String idSchedule;
    private String nameSchedule;
    private String description;

    private Integer votesApproving;
    private Integer votesNotApproving;

    private Date startTimeDate;

    private int scheduleTimeOpenMinut;
}