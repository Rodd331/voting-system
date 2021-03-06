package com.voting.domain.repository;

import com.voting.domain.entity.ScheduleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<ScheduleEntity, String> {

    void deleteByIdSchedule(String idSchedule);

    ScheduleEntity findByIdSchedule(String idSchedule);

    List<ScheduleEntity> findAllByStartTimeDateIsNotNull();
}