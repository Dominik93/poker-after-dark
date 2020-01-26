package com.slusarz.pokerafterdark.domain.player;

import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import com.slusarz.pokerafterdark.domain.validation.ValidationException;
import org.junit.Assert;
import org.junit.Test;

import static com.slusarz.pokerafterdark.domain.AssertValidationUtil.assertValidationMessage;

public class PlayerTest {

    @Test
    public void shouldThrowExceptionWhenPlayerIsAllNull() {
        try {
            Player.of(null, null, null, null);
        } catch (ValidationException e) {
            Assert.assertEquals(4, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.MANDATORY_PLAYER_ID);
            assertValidationMessage(e, ValidationError.MANDATORY_PLAYER_NAME);
            assertValidationMessage(e, ValidationError.MANDATORY_LIVE_WINNINGS);
            assertValidationMessage(e, ValidationError.MANDATORY_NUMBER_OF_PLAYS);
        }
    }

    @Test
    public void shouldThrowExceptionWhenNumberOfPlaysIsBelowZero() {
        try {
            Player.of(PlayerId.of("PLAYER_1"), PlayerName.of("PLAYER_NAME_1"), Earnings.zero(), NumberOfPlays.of(-5));
        } catch (ValidationException e) {
            Assert.assertEquals(1, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.NUMBER_OF_PLAYS_BELOW_ZERO);
        }
    }

    @Test
    public void shouldThrowExceptionWhenPlayerNameIsBlank() {
        try {
            Player.of(PlayerId.of(""), PlayerName.of(""), Earnings.zero(), NumberOfPlays.of(5));
        } catch (ValidationException e) {
            Assert.assertEquals(1, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.MANDATORY_PLAYER_NAME);
        }
    }


}
