package com.voting.system.src.impl.service;

import com.voting.system.src.impl.model.VoteModel;
import com.voting.system.src.impl.repository.ScheduleRepository;
import com.voting.system.src.impl.repository.UserRepository;
import com.voting.system.src.stubs.VoteModelStub;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.voting.system.src.stubs.ScheduleEntityStub.generationScheduleEntity;
import static com.voting.system.src.stubs.UserEntityStub.generationUserEntity;
import static com.voting.system.src.stubs.VoteModelStub.generationVoteModel;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidsServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    UserRepository userRepository;

    @Mock
    ScheduleRepository scheduleRepository;

    @InjectMocks
    ValidsService validsService;

  /*  @Test
    public void insertVote() {
        VoteModel vote = generationVoteModel();
        when(scheduleRepository.findById(any())).thenReturn(Optional.of(generationScheduleEntity()));
        when(scheduleRepository.save(any())).thenReturn(generationScheduleEntity());
        validsService.insertVote(vote);
        verify(validsService).insertVote(VoteModelStub.generationVoteModel());
    }*/

    @Test
    public void validatorListCPF() {
        when(scheduleRepository.findByIdSchedule(any())).thenReturn(generationScheduleEntity());
        validsService.validatorListCPF(generationVoteModel());
        verify(scheduleRepository).findByIdSchedule(generationVoteModel().getIdSchedule());
    }

    @Test
    public void validatorIdSchedule() {
        when(scheduleRepository.existsById(any())).thenReturn(true);
        validsService.validatorIdSchedule(generationVoteModel());
        verify(scheduleRepository).existsById(generationVoteModel().getIdSchedule());
    }

    @Test
    public void validatorTimeSchedule() {
        when(scheduleRepository.findById(any())).thenReturn(Optional.of(generationScheduleEntity()));
        validsService.validatorTimeSchedule(generationVoteModel());
        verify(scheduleRepository).findById(generationVoteModel().getIdSchedule());
    }

    @Test
    public void validatorPassword() {
        VoteModel vote = generationVoteModel();
        when(userRepository.findByCpf(any())).thenReturn(Optional.of(generationUserEntity()));
        validsService.validatorPassword(vote);
        verify(userRepository).findByCpf(generationVoteModel().getCpf());
    }
}