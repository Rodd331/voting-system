package com.voting.system.src.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voting.system.src.impl.entity.ScheduleEntity;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static com.voting.system.src.stubs.ScheduleEntityStub.generationScheduleEntity;
import static com.voting.system.src.stubs.UserEntityStub.generationUserEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AdminController.class)
@ContextConfiguration(classes = {AdminController.class,
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

    @MockBean
    private UserService userService;

    @MockBean
    private ScheduleService scheduleService;

    @Test
    public void deleteUser_ReturnCode_NoContent() throws Exception {
        given(userRepository.deleteByCpf("01905793073")).willReturn(Optional.of(generationUserEntity()));
        this.mockMvc.perform(delete("/api/v1/admin/user/01905793073")).andExpect(status().isNoContent());
    }

    @Test
    public void allUsers_ReturnCode_Ok() throws Exception {
        List<UserEntity> teste = new ArrayList<UserEntity>();
        teste.add(generationUserEntity());
        given(userRepository.findAll()).willReturn(teste);
        this.mockMvc.perform(get("/api/v1/admin/user/")).andExpect(status().isOk());
    }

    @Test
    public void createSchedule_ReturnCode_Created() throws Exception {
        given(scheduleService.createSchedule(any())).willReturn(generationScheduleEntity());
        this.mockMvc.perform(post("/api/v1/admin/schedule/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper()
                        .writeValueAsString(ScheduleEntity.builder().idSchedule("someid").nameSchedule("Teste01")
                                .description("Testando metodo").votesApproving(3).votesNotApproving(2)
                                .cpfVoted(Collections.emptyList()).startTimeDate(new Date()).build())))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteSchedule_ReturnCode_NoContent() throws Exception {
        given(scheduleRepository.deleteByIdSchedule(any())).willReturn(generationScheduleEntity());
        this.mockMvc.perform(delete("/api/v1/admin/schedule/01905793073")).andExpect(status().isNoContent());
    }

   /* @Test
    public void openSchedule_ReturnCode_OK() throws Exception {
        given(scheduleRepository.save(any())).willReturn(generationScheduleEntity());
        this.mockMvc.perform(put("/api/v1/admin/schedule/open/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper()
                        .writeValueAsString(ScheduleEntity.builder().idSchedule("someid").nameSchedule("Teste01")
                                .description("Testando metodo").votesApproving(3).votesNotApproving(2)
                                .cpfVoted(Collections.emptyList()).startTimeDate(new Date()).build()))).andExpect(status().isOk());
    }*/
}