package com.voting.system.src.impl.service;

import com.voting.system.src.impl.entity.UserEntity;
import com.voting.system.src.impl.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(String cpf) {
        userRepository.deleteByCpf(cpf);}

    public List<UserEntity> listAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findByCPF(String cpf) {
        return userRepository.findByCpf(cpf);}


}
