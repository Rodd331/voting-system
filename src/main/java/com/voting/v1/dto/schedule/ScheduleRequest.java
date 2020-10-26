package com.voting.v1.dto.schedule;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleRequest {

    @NotBlank(message = "Field needs to be filled")
    private String nameSchedule;

    @NotBlank(message = "Field needs to be filled")
    private String description;

    private int scheduleTimeOpenMinut;
}