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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.voting.system.src.stubs.ScheduleEntityStub.generationScheduleEntity;
import static com.voting.system.src.stubs.UserEntityStub.generationUserEntity;
import static com.voting.system.src.stubs.UserEntityStub.generationUserEntity2;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void findByCPF_ReturnCode_isOk() throws Exception {
        given(userRepository.findByCpf("01905793073")).willReturn(java.util.Optional.of(generationUserEntity2()));
        this.mockMvc.perform(get("/api/v1/user/01905793073"))
                .andExpect(status().isOk());
    }

    @Test
    public void createUser_ReturnCode_Created() throws Exception {
        given(userRepository.save(any())).willReturn(generationUserEntity());
        this.mockMvc.perform(post("/api/v1/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper()
                        .writeValueAsString(UserEntity.builder().idUser("someid").cpf("01905793073")
                                .password("123456").build())))
                .andExpect(status().isCreated());
    }

    @Test
    public void allSchedules_ReturnCode_OK() throws Exception {
        List<ScheduleEntity> teste = new ArrayList<ScheduleEntity>();
        teste.add(generationScheduleEntity());
        given(scheduleRepository.findAll()).willReturn(teste);
        this.mockMvc.perform(get("/api/v1/user/schedule/")).andExpect(status().isOk());
    }

    @Test
    public void findByIdSchedule_ReturnCode_isOk() throws Exception {
        given(scheduleRepository.findById("123456")).willReturn(Optional.of(generationScheduleEntity()));
        given(scheduleRepository.findByIdSchedule("123456")).willReturn(generationScheduleEntity());
        this.mockMvc.perform(get("/api/v1/user/schedule/123456"))
                .andExpect(status().isOk());
    }
}