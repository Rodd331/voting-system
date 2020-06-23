package com.voting.system.src.impl.repository;

import com.voting.system.src.impl.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> deleteByCpf(String cpf);
    UserEntity findByCpf(String cpf);
}
