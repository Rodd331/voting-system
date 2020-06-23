package com.voting.system.src.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleListResponse {

    private List<ScheduleResponse> scheduleResponseList;
    private int size;
}
