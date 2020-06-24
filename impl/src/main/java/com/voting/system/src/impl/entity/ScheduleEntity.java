package com.voting.system.src.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
@Document(collection = "Schedules")
public class ScheduleEntity {

    @Id
    String idSchedule;

    String nameSchedule;
    String description;

    Integer votesApproving;
    Integer votesNotApproving;

    List<String> cpfVoted;

    Date startTimeDate;

    int scheduleTimeOpenMinut;
}
