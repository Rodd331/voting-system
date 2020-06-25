package com.voting.system.src.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.facade.ScheduleImplFacade;
import com.voting.system.src.impl.facade.UserImplFacade;
import com.voting.system.src.impl.model.VoteModel;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.voting.system.src.stubs.ScheduleEntityStub.generationScheduleEntity;
import static com.voting.system.src.stubs.UserEntityStub.generationUserEntity;
import static com.voting.system.src.stubs.VoteModelStub.generationVoteModel;
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
public class AdminControllerTest2 extends TestCase {

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

    @MockBean
    private ValidsService validsService;

    @Test
    public void allOpenSchedules_ReturnCode_OK() throws Exception {
        List<ScheduleEntity> teste = new ArrayList<ScheduleEntity>();
        teste.add(generationScheduleEntity());
        given(scheduleService.listAllOpenSchedules()).willReturn(teste);
        this.mockMvc.perform(get("/api/v1/user/openSchedule/")).andExpect(status().isOk());
    }

    @Test
    public void voteUser_ReturnCode_Ok() throws Exception {
        VoteModel vote = generationVoteModel();
        given(userRepository.findByCpf(any())).willReturn(Optional.of(generationUserEntity()));
        given(scheduleRepository.existsById(vote.getIdSchedule())).willReturn(true);
        given(scheduleRepository.findById(vote.getIdSchedule())).willReturn(Optional.of(generationScheduleEntity()));
        given(scheduleRepository.findByIdSchedule(vote.getIdSchedule())).willReturn(generationScheduleEntity());
        given(validsService.validatorCPF(any())).willReturn(true);
        given(scheduleRepository.save(any())).willReturn(generationScheduleEntity());
        this.mockMvc.perform(post("/api/v1/user/vote/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper()
                        .writeValueAsString(VoteModel.builder().cpf("01905793073")
                                .password("123456").vote("Sim").idSchedule("someId").build())))
                .andExpect(status().isOk());
    }

}