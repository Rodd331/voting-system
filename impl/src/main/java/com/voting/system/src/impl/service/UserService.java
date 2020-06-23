package com.voting.system.src.impl.service;

import com.voting.system.src.impl.entity.UserEntity;
import com.voting.system.src.impl.handler.ApiException;
import com.voting.system.src.impl.repository.ScheduleRepository;
import com.voting.system.src.impl.repository.UserRepository;
import com.voting.system.src.impl.utils.Validations;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements Validations {

    private UserRepository userRepository;
    private ScheduleRepository scheduleRepository;

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(String cpf) {
        userRepository.deleteByCpf(cpf).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public List<UserEntity> listAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findByCPF(String cpf) {
        return userRepository.findByCpf(cpf)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public void validatorId(String id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Id not found");
        }
    }

    @Override
    public void validationEmptyList() {
        if (userRepository.findAll().isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "There are no registered users");
        }
    }
}
