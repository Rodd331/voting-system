package com.voting.system.src.v1;

import com.voting.system.src.impl.entity.UserEntity;
import com.voting.system.src.impl.facade.ScheduleImplFacade;
import com.voting.system.src.impl.facade.UserImplFacade;
import com.voting.system.src.impl.repository.ScheduleRepository;
import com.voting.system.src.impl.repository.UserRepository;
import com.voting.system.src.impl.service.ScheduleService;
import com.voting.system.src.impl.service.UserService;
import com.voting.system.src.impl.service.ValidsService;
import com.voting.system.src.v1.facade.ScheduleContractFacade;
import com.voting.system.src.v1.facade.UserContractFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.voting.system.src.stubs.UserEntityStub.generationUserEntity;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AdminControllerTest.class)
@ContextConfiguration(classes = {AdminControllerTest.class,
        UserContractFacade.class,
        UserImplFacade.class,
        ValidsService.class,
        ScheduleContractFacade.class,
        ScheduleImplFacade.class,
        ScheduleService.class,
        UserService.class})
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ScheduleRepository scheduleRepository;


    @Test
    public void deleteUser_ReturnCode_NotFound() throws Exception {
        UserEntity userEntityExample = new UserEntity("someid", "01905793073", "123456");
        given(userRepository.deleteByCpf("01905793073")).willReturn(Optional.empty());
        this.mockMvc.perform(delete("/api/v1/admin/user/01905793073")).andExpect(status().isNotFound());
    }

    @Test
    public void allUsers_ReturnCode_NotFound() throws Exception {
        List<UserEntity> teste = new ArrayList<UserEntity>();
        teste.add(generationUserEntity());
        given(userRepository.findAll()).willReturn(teste);
        this.mockMvc.perform(get("/api/v1/admin/user/")).andExpect(status().isNotFound());
    }
}