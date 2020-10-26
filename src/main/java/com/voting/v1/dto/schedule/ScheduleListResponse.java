package com.voting.v1.dto.schedule;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleListResponse {

    private List<ScheduleResponse> scheduleResponseList;
    private int size;
}