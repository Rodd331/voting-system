package com.voting.system.src.v1.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleRequest {

    @NotBlank(message = "Field needs to be filled")
    String nameSchedule;

    @NotBlank(message = "Field needs to be filled")
    String description;

    @NotBlank(message = "Field needs to be filled")
    Date startTimeDate;

    private int scheduleTimeOpenMinut;
}
