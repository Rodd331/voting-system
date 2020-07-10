package com.voting.system.src.impl.model.schedule;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@lombok.Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
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