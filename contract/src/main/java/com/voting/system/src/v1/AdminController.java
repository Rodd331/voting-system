package com.voting.system.src.v1;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Api Admin Controller", tags = " Api")
@RequestMapping(path = "/api/v1/admin")
@RestController
@AllArgsConstructor
public class AdminController {
}
