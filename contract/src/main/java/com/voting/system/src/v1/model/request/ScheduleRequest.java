package com.voting.system.src.v1.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleRequest {

    String idSchedule;

    @NotBlank(message = "Field needs to be filled")
    String nameSchedule;

    @NotBlank(message = "Field needs to be filled")
    String description;

    Integer votesApproving;
    Integer votesNotApproving;

    List<Integer> cpfVoted;

    @NotBlank(message = "Field needs to be filled")
    Date startTimeDate;
    Date endTimeDate;
    Date dateCreationSchedule;
}
