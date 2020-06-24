package com.voting.system.src.v1;

import com.voting.system.src.impl.facade.ScheduleImplFacade;
import com.voting.system.src.impl.facade.UserImplFacade;
import com.voting.system.src.impl.repository.ScheduleRepository;
import com.voting.system.src.impl.repository.UserRepository;
import com.voting.system.src.impl.service.ScheduleService;
import com.voting.system.src.impl.service.UserService;
import com.voting.system.src.impl.service.ValidsService;
import com.voting.system.src.v1.facade.ScheduleContractFacade;
import com.voting.system.src.v1.facade.UserContractFacade;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.voting.system.src.stubs.UserEntityStub.generationUserEntity2;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = {UserController.class,
        UserContractFacade.class,
        UserImplFacade.class,
        ValidsService.class,
        ScheduleContractFacade.class,
        ScheduleImplFacade.class,
        ScheduleService.class,
        UserService.class})
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @Test
    public void findById_ReturnCode_NotFound() throws Exception {
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(generationUserEntity2()));
        this.mockMvc.perform(get("/crud/v1/user/someid"))
                .andExpect(status().isNotFound());
    }

}