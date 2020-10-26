package com.voting.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
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

    @JsonIgnore
    @Builder.Default
    String messageAlreadySent = "N";
}