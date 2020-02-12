package com.slusarz.pokerafterdark.domain.game;

import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import com.slusarz.pokerafterdark.domain.validation.ValidationException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.slusarz.pokerafterdark.domain.AssertValidationUtil.assertValidationMessage;

public class GameTest {

    @Test
    public void shouldThrowExceptionWhenGameIdIsBlank() {
        List<Participant> participants = new ArrayList<>();
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_2"), Earnings.of(-20)));
        try {
            Game.of(GameId.of(""), PlayerId.of(""), LocalDate.now(), Pot.of(50), participants);
        } catch (ValidationException e) {
            Assert.assertEquals(1, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.MANDATORY_GAME_ID);
        }
    }

    @Test
    public void shouldThrowExceptionWhenGameIsAllNull() {
        try {
            Game.of(null, null, null, null, null);
        } catch (ValidationException e) {
            Assert.assertEquals(5, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.MANDATORY_POT);
            assertValidationMessage(e, ValidationError.MANDATORY_GAME_DATE);
            assertValidationMessage(e, ValidationError.MANDATORY_GAME_ID);
            assertValidationMessage(e, ValidationError.MANDATORY_HOST_ID);
            assertValidationMessage(e, ValidationError.EMPTY_PARTICIPANTS);
        }
    }

    @Test
    public void shouldThrowExceptionWhenPotIsNegativeNumber() {
        List<Participant> participants = new ArrayList<>();
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_2"), Earnings.of(-20)));
        try {
            Game.of(GameId.of("GAME_1"), PlayerId.of("PLAYER_1"), LocalDate.now(), Pot.of(-20), participants);
        } catch (ValidationException e) {
            Assert.assertEquals(1, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.POT_BELOW_ZERO);
        }
    }

    @Test
    public void shouldThrowExceptionWhenParticipantsListIsEmpty() {
        try {
            Game.of(GameId.of("GAME_1"), PlayerId.of("PLAYER_1"), LocalDate.now(), Pot.of(20), Collections.emptyList());
        } catch (ValidationException e) {
            Assert.assertEquals(1, e.getConstraintViolations().size());
            assertValidationMessage(e, ValidationError.EMPTY_PARTICIPANTS);
        }
    }

}
