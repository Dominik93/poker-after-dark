package com.slusarz.pokerafterdark.domain.cashgame;

import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import com.slusarz.pokerafterdark.domain.validation.ValidationException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.slusarz.pokerafterdark.domain.AssertValidationUtil.assertValidationMessage;

public class CashGameTest {

    @Test
    public void shouldThrowExceptionWhenGameIdIsBlank() {
        List<CashGameParticipant> cashGameParticipants = new ArrayList<>();
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_2"), Earnings.of(-20)));
        try {
            CashGame.of(CashGameId.of(""), PlayerId.of(""), LocalDate.now(), Pot.of(50), cashGameParticipants);
        } catch (ValidationException e) {
            Assert.assertEquals(1, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.MANDATORY_GAME_ID);
        }
    }

    @Test
    public void shouldThrowExceptionWhenGameIsAllNull() {
        try {
            CashGame.of(null, null, null, null, null);
        } catch (ValidationException e) {
            Assert.assertEquals(5, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.MANDATORY_POT);
            assertValidationMessage(e, ValidationError.MANDATORY_GAME_DATE);
            assertValidationMessage(e, ValidationError.MANDATORY_GAME_ID);
            assertValidationMessage(e, ValidationError.MANDATORY_HOST_ID);
            assertValidationMessage(e, ValidationError.EMPTY_CASH_GAME_PARTICIPANTS);
        }
    }

    @Test
    public void shouldThrowExceptionWhenPotIsNegativeNumber() {
        List<CashGameParticipant> cashGameParticipants = new ArrayList<>();
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_2"), Earnings.of(-20)));
        try {
            CashGame.of(CashGameId.of("GAME_1"), PlayerId.of("PLAYER_1"), LocalDate.now(), Pot.of(-20), cashGameParticipants);
        } catch (ValidationException e) {
            Assert.assertEquals(1, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.POT_BELOW_ZERO);
        }
    }

    @Test
    public void shouldThrowExceptionWhenParticipantsListIsEmpty() {
        try {
            CashGame.of(CashGameId.of("GAME_1"), PlayerId.of("PLAYER_1"), LocalDate.now(), Pot.of(20), Collections.emptyList());
        } catch (ValidationException e) {
            Assert.assertEquals(1, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.EMPTY_CASH_GAME_PARTICIPANTS);
        }
    }

}
