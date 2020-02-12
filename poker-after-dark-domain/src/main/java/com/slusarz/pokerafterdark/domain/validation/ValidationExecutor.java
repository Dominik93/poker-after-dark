package com.slusarz.pokerafterdark.domain.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidationExecutor {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static <T> T validateAndReturn(T validatedObject) {
        validate(validatedObject);
        return validatedObject;
    }

    public static void validate(Object validatedObject) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(validatedObject);

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }

}