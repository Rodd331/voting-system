package com.voting.v1.dto.schedule;

import lombok.*;

import java.util.Date;

@Getter
@Setter
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