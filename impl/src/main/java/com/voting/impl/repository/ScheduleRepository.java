package com.voting.impl.repository;

import com.voting.impl.entity.ScheduleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<ScheduleEntity, String> {

    void deleteByIdSchedule(String idSchedule);

    ScheduleEntity findByIdSchedule(String idSchedule);

    List<ScheduleEntity> findAllByStartTimeDateIsNotNull();
}