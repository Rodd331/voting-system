package com.voting.v1;

import com.voting.impl.handler.ExceptionResponse;
import com.voting.v1.facade.ScheduleContractFacade;
import com.voting.v1.model.request.ScheduleRequest;
import com.voting.v1.model.response.ScheduleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Api Admin Controller", tags = " Api")
@RequestMapping(path = "/api/v1/admin")
@RestController
@AllArgsConstructor
public class AdminController {

    private final ScheduleContractFacade scheduleContractFacade;

    @ApiOperation(value = "Create new schedule.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Schedule created"),
            @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionResponse.class)
    })
    @PostMapping("/schedule/")
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleResponse createSchedule(@Valid @RequestBody ScheduleRequest schedule) {
        return scheduleContractFacade.createSchedule(schedule);
    }

    @ApiOperation(value = "Delete schedule.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted schedule"),
            @ApiResponse(code = 404, message = "Not found", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)
    })
    @DeleteMapping("/schedule/{idSchedule}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable String idSchedule) {
        scheduleContractFacade.deleteByIdSchedule(idSchedule);
    }

    @ApiOperation(value = "Open schedule.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Open schedule"),
            @ApiResponse(code = 400, message = "Data already registered", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "Schedule not found", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)
    })
    @PutMapping("/schedule/open/{idSchedule}")
    public @Valid void openSchedule(@Valid @PathVariable String idSchedule) {
        scheduleContractFacade.openSchedule(idSchedule);
    }
}