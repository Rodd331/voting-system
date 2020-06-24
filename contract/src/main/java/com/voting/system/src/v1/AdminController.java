package com.voting.system.src.v1;

import com.voting.system.src.impl.handler.ExceptionResponse;
import com.voting.system.src.v1.facade.ScheduleContractFacade;
import com.voting.system.src.v1.facade.UserContractFacade;
import com.voting.system.src.v1.model.request.ScheduleRequest;
import com.voting.system.src.v1.model.response.ScheduleResponse;
import com.voting.system.src.v1.model.response.UserListResponse;
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

    private UserContractFacade userContractFacade;
    private ScheduleContractFacade scheduleContractFacade;

    @ApiOperation(value = "Delete user.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted User"),
            @ApiResponse(code = 404, message = "User not found", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)
    })
    @DeleteMapping("/user/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String cpf) {
        userContractFacade.deleteUser(cpf);
    }

    @ApiOperation(value = "List all users.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 404, message = "Not found", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionResponse.class)
    })
    @GetMapping("/user/")
    public UserListResponse allUsers() {
        return userContractFacade.allUsers();
    }

    @ApiOperation(value = "Create new schedule.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Schedule created"),
            @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionResponse.class)
    })
    @PostMapping("/schedule")
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

    @ApiOperation(value = "Update registered schedule.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated schedule"),
            @ApiResponse(code = 400, message = "Data already registered", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "Schedule not found", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)
    })
    @PutMapping("/schedule/{idSchedule}")
    public @Valid ScheduleResponse updateSchedule(@Valid @RequestBody ScheduleRequest schedule, @PathVariable String idSchedule) {
        return scheduleContractFacade.updateSchedule(schedule, idSchedule);
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

