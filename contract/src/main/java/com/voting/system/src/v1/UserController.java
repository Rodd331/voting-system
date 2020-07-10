package com.voting.system.src.v1;

import com.voting.system.src.impl.handler.ExceptionResponse;
import com.voting.system.src.v1.facade.ScheduleContractFacade;
import com.voting.system.src.v1.model.request.VoteRequest;
import com.voting.system.src.v1.model.response.ScheduleListResponse;
import com.voting.system.src.v1.model.response.ScheduleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Api User Controller", tags = "Api")
@RequestMapping(path = "/api/v1")
@RestController
@AllArgsConstructor
public class UserController {

    private ScheduleContractFacade scheduleContractFacade;

    @ApiOperation(value = "Vote.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionResponse.class)
    })
    @PostMapping("/user/vote/")
    @ResponseStatus(HttpStatus.OK)
    public void voteUser(@Valid @RequestBody VoteRequest vote) {
        scheduleContractFacade.vote(vote);
    }

    @ApiOperation(value = "List all schedules.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 404, message = "Not found", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionResponse.class)
    })
    @GetMapping("/user/schedule/")
    public ScheduleListResponse allSchedules() {
        return scheduleContractFacade.allSchedules();
    }

    @ApiOperation(value = "List all open schedules.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 404, message = "Not found", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionResponse.class)
    })
    @GetMapping("/user/openSchedule/")
    public ScheduleListResponse allOpenSchedules() {
        return scheduleContractFacade.allOpenSchedules();
    }

    @ApiOperation(value = "Search for idSchedule.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Schedules found"),
            @ApiResponse(code = 404, message = "Schedules not found", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionResponse.class)
    })
    @GetMapping("/user/schedule/{idSchedule}")
    public ScheduleResponse findByIdSchedule(@PathVariable String idSchedule) {
        return scheduleContractFacade.findByIdSchedule(idSchedule);
    }
}