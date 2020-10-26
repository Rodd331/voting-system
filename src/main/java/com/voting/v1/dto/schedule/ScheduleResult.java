package com.voting.v1.dto.schedule;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class ScheduleResult {

    private String idSchedule;
    private String nameSchedule;
    private String description;
    private Integer votesApproving;
    private Integer votesNotApproving;
    private String result;
}