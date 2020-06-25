package com.voting.system.src.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@lombok.Data
@Builder
public class ScheduleModel {

    String idSchedule;
    String nameSchedule;
    String description;

    Integer votesApproving;
    Integer votesNotApproving;

    List<String> cpfVoted;

    Date startTimeDate;

    int scheduleTimeOpenMinut;
}