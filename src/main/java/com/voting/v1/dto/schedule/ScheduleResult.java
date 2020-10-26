package com.voting.v1.dto.schedule;

import lombok.*;

@Getter
@Setter
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