package com.voting.system.src.impl.utils;

import org.springframework.stereotype.Component;

@Component
public interface Validations {

    void validatorId(String id);
    void validationEmptyList();
}
