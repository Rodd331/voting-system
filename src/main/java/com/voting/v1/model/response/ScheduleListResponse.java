package com.voting.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleListResponse {

    private List<ScheduleResponse> scheduleResponseList;
    private int size;
}