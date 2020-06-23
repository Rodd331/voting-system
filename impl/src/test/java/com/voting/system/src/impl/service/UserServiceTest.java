package com.voting.system.src.impl.service;

import com.voting.system.src.impl.entity.UserEntity;
import com.voting.system.src.impl.repository.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static com.voting.system.src.stubs.UserEntityStub.generationUserEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void createUser() {

        when(userRepository.save(any())).thenReturn(generationUserEntity());
        userService.createUser(generationUserEntity());
        verify(userRepository).save(generationUserEntity());
    }

    @Test
    public void deleteUserException() {
        expectedException.expectMessage("User not found");
        userService.deleteUser("someid");
        verify(userRepository).deleteByCpf("someid");
    }

    @Test
    public void deleteUserIsOk() {
        when(userRepository.deleteByCpf(any())).thenReturn(Optional.of(generationUserEntity()));
        userService.deleteUser("someid");
        verify(userRepository).deleteByCpf("someid");
    }

    @Test
    public void listAllUsersReturn() {
        List<UserEntity> teste = new ArrayList<>();
        teste.add(generationUserEntity());

        when(userRepository.findAll()).thenReturn(teste);
        userService.listAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    public void findByCPF() {
        when(userRepository.findByCpf(any())).thenReturn(Optional.of(generationUserEntity()));
        userService.findByCPF("someid");
        verify(userRepository).findByCpf("someid");
    }

    @Test
    public void findByCPFException() {
        expectedException.expectMessage("User not found");
        userService.findByCPF("someid");
        verify(userRepository).findByCpf("someid");
    }

    @Test
    public void validatorUserIdException() {
        expectedException.expectMessage("Id not found");
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        userService.validatorId("someid");
    }

    @Test
    public void validatorUserId() {
        when(userRepository.findById(any())).thenReturn(Optional.of(generationUserEntity()));
        userService.validatorId("someid");
        verify(userRepository).findById("someid");
    }

    @Test
    public void validationEmptyListException() {
        expectedException.expectMessage("There are no registered users");
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        userService.validationEmptyList();
    }

    @Test
    public void validationEmptyList() {
        List teste = new ArrayList();
        teste.add(generationUserEntity());
        when(userRepository.findAll()).thenReturn(teste);
        userService.validationEmptyList();
        verify(userRepository).findAll();
    }
}