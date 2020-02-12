package com.slusarz.pokerafterdark.addgame;

import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameValidator;
import com.slusarz.pokerafterdark.application.usecase.addgame.exception.PotNotMatchRuntimeException;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class AddGameValidatorStepdefs {

    private AddGameValidator addGameValidator;

    private ExceptionsHandler exceptionsHandler;

    private List<Participant> participants = new ArrayList<>();

    @Before
    public void setUp() {
        GameQueryRepository gameQueryRepository = mock(GameQueryRepository.class);
        addGameValidator = new AddGameValidator(gameQueryRepository);
        exceptionsHandler = new ExceptionsHandler();
        participants = new ArrayList<>();
    }

    @Given("^Participants with pot sum to zero")
    public void participiantsWithPotSumTo() {
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(-20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(0)));
    }

    @When("^Validate participations$")
    public void validateParticipations() {
        try {
            addGameValidator.validatePot(participants);
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
    }

    @Then("^Validation pass$")
    public void validationPass() {
        Assert.assertEquals(0, exceptionsHandler.size(PotNotMatchRuntimeException.class));
    }

    @Given("^Participants with pot not sum zero")
    public void participantsWithPotNotSum() {
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(0)));

    }

    @Then("^Validation failed$")
    public void validationFailed() {
        Assert.assertEquals(1, exceptionsHandler.size(PotNotMatchRuntimeException.class));
    }
}
