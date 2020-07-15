package com.slusarz.pokerafterdark.domain;

import com.slusarz.pokerafterdark.domain.validation.ValidationException;
import lombok.NoArgsConstructor;
import org.junit.Assert;

import javax.validation.ConstraintViolation;

@NoArgsConstructor
public class AssertValidationUtil {

    public static void assertValidationMessage(ValidationException ex, String expectedMessage) {
        Assert.assertTrue(ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .anyMatch(message -> message.equals(expectedMessage)));
    }

}
