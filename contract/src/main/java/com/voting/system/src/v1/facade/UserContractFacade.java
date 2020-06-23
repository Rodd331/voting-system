package com.voting.system.src.v1.facade;

import com.voting.system.src.impl.facade.UserImplFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserContractFacade {

    private UserImplFacade userImplFacade;
}
