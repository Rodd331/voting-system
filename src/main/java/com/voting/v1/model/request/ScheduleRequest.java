package com.voting.v1.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleRequest {

    @NotBlank(message = "Field needs to be filled")
    private String nameSchedule;

    @NotBlank(message = "Field needs to be filled")
    private String description;

    private int scheduleTimeOpenMinut;
}