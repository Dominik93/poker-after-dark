package com.slusarz.pokerafterdark.domain.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {

    private Set<ConstraintViolation<Object>> constraintViolations;

}
