/*
package com.voting.system.src.impl.service;

import com.voting.system.src.impl.integration.Integration;
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

import static com.voting.system.src.impl.integration.Integration.consumerCPF;
import static com.voting.system.src.stubs.UserEntityStub.generationUserEntity;
import static com.voting.system.src.stubs.VoteModelStub.generationVoteModel;
import static org.junit.Assert.*;
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

    @Test
    public void insertVote() {
        when(scheduleRepository.save(any())).thenReturn(generationVoteModel());
        when(scheduleRepository.save(any())).thenReturn(generationVoteModel());
        validsService.insertVote(VoteModelStub.generationVoteModel());
        verify(validsService).insertVote(VoteModelStub.generationVoteModel());
    }
}
*/