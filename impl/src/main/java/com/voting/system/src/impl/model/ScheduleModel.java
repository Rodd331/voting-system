package com.voting.system.src.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ScheduleModel {

    String idSchedule;
    String nameSchedule;
    String description;

    Integer votesApproving;
    Integer votesNotApproving;

    List<String> cpfVoted;

    Date startTimeDate;
    Date endTimeDate;
    Date dateCreationSchedule;

    private int scheduleTimeOpenMinut;

}
