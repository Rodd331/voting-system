package com.voting.system.src.v1;

import com.voting.system.src.impl.handler.ExceptionResponse;
import com.voting.system.src.v1.facade.UserContractFacade;
import com.voting.system.src.v1.model.response.UserListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Api Admin Controller", tags = " Api")
@RequestMapping(path = "/api/v1/admin")
@RestController
@AllArgsConstructor
public class AdminController {

    private UserContractFacade userContractFacade;

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

}
