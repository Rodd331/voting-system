package com.voting.system.src.impl.repository;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends MongoRepository<ScheduleEntity, String> {

    Optional<UserEntity> deleteByIdSchedule(String idPauta);
}
