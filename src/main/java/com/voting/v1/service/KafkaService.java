package com.voting.v1.service;

import com.voting.kafka.EventProducer;
import com.voting.v1.dto.schedule.ScheduleResult;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaService {

    private final EventProducer eventProducer;

    @Value("${session-result.kafka.topic}")
    private final String topic;

    public void send(ProducerRecord<String, Object> producerRecord) {
        eventProducer.send(producerRecord);
    }

    public ProducerRecord<String, Object> makeRecord(ScheduleResult scheduleResult) {
        return new ProducerRecord<>(topic, scheduleResult.getIdSchedule(),
            scheduleResult);
    }
}