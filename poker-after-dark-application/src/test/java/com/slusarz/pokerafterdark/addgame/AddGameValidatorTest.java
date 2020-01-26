package com.slusarz.pokerafterdark.addgame;

import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameValidator;
import com.slusarz.pokerafterdark.application.usecase.addgame.exception.PotNotMatchRuntimeException;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class AddGameValidatorTest {

    private AddGameValidator addGameValidator;

    private ExceptionsHandler exceptionsHandler;

    @BeforeEach
    public void setUp() {
        GameQueryRepository gameQueryRepository = mock(GameQueryRepository.class);
        addGameValidator = new AddGameValidator(gameQueryRepository);
        exceptionsHandler = new ExceptionsHandler();
    }

    @Test
    public void shouldPassValidationBecauseEarningsSumToZero() {
        List<Participant> participants = new ArrayList<>();
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(-20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(0)));
        try {
            addGameValidator.validatePot(participants);
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
        Assert.assertEquals(0, exceptionsHandler.size(PotNotMatchRuntimeException.class));
    }

    @Test
    public void shouldFailValidationBecauseEarningsNotSumToZero() {
        List<Participant> participants = new ArrayList<>();
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(0)));
        try {
            addGameValidator.validatePot(participants);
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
        Assert.assertEquals(1, exceptionsHandler.size(PotNotMatchRuntimeException.class));
    }

}
