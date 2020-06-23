package com.voting.system.src.v1;

import com.voting.system.src.impl.handler.ExceptionResponse;
import com.voting.system.src.v1.facade.UserContractFacade;
import com.voting.system.src.v1.model.request.UserRequest;
import com.voting.system.src.v1.model.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Api User Controller", tags = " Api")
@RequestMapping(path = "/api/v1")
@RestController
@AllArgsConstructor
public class UserController {

    private UserContractFacade userContractFacade;

    @ApiOperation(value = "Create new user.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created"),
            @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionResponse.class)
    })
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest user) {
        return userContractFacade.createUser(user);
    }

    @ApiOperation(value = "Search for CPF.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionResponse.class)
    })
    @GetMapping("/user/{cpf}")
    public UserResponse findByCPF(@PathVariable String cpf) {
        return userContractFacade.findByCPF(cpf);
    }

}

