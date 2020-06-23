package com.voting.system.src.impl.facade;

import com.voting.system.src.impl.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserImplFacade {

    private UserService userService;
}
