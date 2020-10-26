package com.voting.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "Schedules")
public class ScheduleEntity {

    @Id
    private String idSchedule;
    private String nameSchedule;
    private String description;

    private Integer votesApproving;
    private Integer votesNotApproving;

    private List<String> cpfVoted;

    private Date startTimeDate;

    private int scheduleTimeOpenMinut;

    @JsonIgnore
    @Builder.Default
    private String messageAlreadySent = "N";
}