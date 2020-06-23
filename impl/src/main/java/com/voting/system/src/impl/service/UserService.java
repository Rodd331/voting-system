package com.voting.system.src.impl.service;

import com.voting.system.src.impl.entity.UserEntity;
import com.voting.system.src.impl.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }
}
