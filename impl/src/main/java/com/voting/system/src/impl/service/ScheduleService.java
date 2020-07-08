package com.voting.system.src.impl.service;

import com.voting.system.src.impl.entity.ScheduleEntity;
import com.voting.system.src.impl.handler.ApiException;
import com.voting.system.src.impl.repository.ScheduleRepository;
import com.voting.system.src.impl.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.voting.system.src.impl.integration.Integration.runConsumer;

@Service
@AllArgsConstructor
@EnableScheduling
public class ScheduleService implements Utils {

    private ScheduleRepository scheduleRepository;

    public ScheduleEntity createSchedule(ScheduleEntity schedule) {
        int minutes = schedule.getScheduleTimeOpenMinut();

        if (minutes == 0) {
            minutes = 1;
        }
        return scheduleRepository.save(schedule);
    }

    public void deleteByIdSchedule(String idSchedule) {
        scheduleRepository.deleteByIdSchedule(idSchedule);
    }

    public List<ScheduleEntity> listAllSchedule() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> listAllOpenSchedules() {
        List<ScheduleEntity> lista = scheduleRepository.findAll();
        try {
            lista.stream().forEach(list -> {
                if (!list.getStartTimeDate().before(adcMinut(list.getStartTimeDate(), list.getScheduleTimeOpenMinut()))) {
                    lista.remove(list);
                }
            });
            return lista;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Open schedule not found");
        }
    }

    public ScheduleEntity findByIdSchedule(String idSchedule) {
        return scheduleRepository.findById(idSchedule)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));
    }

    public void openSchedule(String idSchedule) {
        ScheduleEntity schedule = scheduleRepository.findByIdSchedule(idSchedule);
        if (schedule.getStartTimeDate() != null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Agenda has already been voted");
        } else {
            schedule.setStartTimeDate(adcMinut(new Date(), -180));
            scheduleRepository.save(schedule);
        }
    }

    @Override
    public void validatorId(String id) {
        if (scheduleRepository.findById(id).isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Id not found");
        }
    }

    @Override
    public void validationEmptyList() {
        if (scheduleRepository.findAll().isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "There are no registered schedules");
        }
    }

    /*  @Override
      public Boolean validatorCPF(String cpf) {
          Vote result = new Vote();
          String status = result.getStatus();

          if (status.equals("U")) {
              throw new ApiException(HttpStatus.UNAUTHORIZED, "User not authorized");
          } else if (!status.equals("A")) {
              throw new ApiException(HttpStatus.BAD_REQUEST, "External error");
          }
          return true;
      }*/
    @Override
    public void validatorCPF(String cpf) {
    }

    @Override
    public Date adcMinut(Date data, Integer minutos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MINUTE, minutos);
        Date dataFinal = cal.getTime();

        return dataFinal;
    }

    /* public void validatorTimeSchedule(VoteModel vote) {
            ScheduleEntity schedule = scheduleRepository.findById(vote.getIdSchedule())
                    .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Schedule not found"));
            Date system = new Date();
            schedule.setStartTimeDate(adcMinut(schedule.getStartTimeDate(), 180));
            Date compare = adcMinut(schedule.getStartTimeDate(), schedule.getScheduleTimeOpenMinut());
            if (system.after(compare)) {
                throw new ApiException(HttpStatus.NOT_FOUND, "Schedule not open");
            }
        }*/

    @Scheduled(fixedDelay = 5000)
    @SneakyThrows
    public void validatorCPF22() {
        System.out.println(runConsumer("01905793073"));
    }
}