package com.voting.system.src.impl.service;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.entity.UserEntity;
import com.voting.system.src.impl.repository.ScheduleRepository;
import com.voting.system.src.impl.repository.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.voting.system.src.stubs.ScheduleEntityStub.generationScheduleEntity;
import static com.voting.system.src.stubs.UserEntityStub.generationUserEntity;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    ScheduleRepository scheduleRepository;

    @InjectMocks
    ScheduleService scheduleService;

    @Test
    public void deleteSchadule() {
        scheduleService.deleteByIdSchedule("someid");
        verify(scheduleRepository).deleteByIdSchedule("someid");
    }

    @Test
    public void listAllSchadule() {
        List<ScheduleEntity> teste = new ArrayList<>();
        teste.add(generationScheduleEntity());

        when(scheduleRepository.findAll()).thenReturn(teste);
        scheduleService.listAllSchedule();
        verify(scheduleRepository).findAll();
    }
/*
    @Test
    public void listAllOpenSchedules() {
        List<ScheduleEntity> teste = new ArrayList<>();
        teste.add(generationScheduleEntity());

        when(scheduleRepository.findAll()).thenReturn(teste);
        scheduleService.listAllOpenSchedules();
        verify(scheduleRepository).findAll();
    }*/

    @Test
    public void findByIdSchedule() {
        when(scheduleRepository.findById(any())).thenReturn(Optional.of(generationScheduleEntity()));
        scheduleService.findByIdSchedule("someid");
        verify(scheduleRepository).findById("someid");
    }
    @Test
    public void validatorScheduleIdException() {
        expectedException.expectMessage("Id not found");
        when(scheduleRepository.findById(any())).thenReturn(Optional.empty());
        scheduleService.validatorId("someid");
    }

    @Test
    public void validatorScheduleId() {
        when(scheduleRepository.findById(any())).thenReturn(Optional.of(generationScheduleEntity()));
        scheduleService.validatorId("someid");
        verify(scheduleRepository).findById("someid");
    }

    @Test
    public void validationEmptyListException() {
        expectedException.expectMessage("There are no registered schedules");
        when(scheduleRepository.findAll()).thenReturn(Collections.emptyList());
        scheduleService.validationEmptyList();
    }

    @Test
    public void validationEmptyList() {
        List teste = new ArrayList();
        teste.add(generationUserEntity());
        when(scheduleRepository.findAll()).thenReturn(teste);
        scheduleService.validationEmptyList();
        verify(scheduleRepository).findAll();
    }
}