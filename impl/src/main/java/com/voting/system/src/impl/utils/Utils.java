package com.voting.system.src.impl.utils;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface Utils {

    void validatorId(String id);

    void validationEmptyList();

    Boolean validatorCPF(String cpf);

    Date adcMinut(Date data, Integer minutos);
}