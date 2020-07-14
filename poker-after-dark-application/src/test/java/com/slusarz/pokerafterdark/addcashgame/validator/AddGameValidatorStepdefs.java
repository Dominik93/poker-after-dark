package com.slusarz.pokerafterdark.addcashgame.validator;

import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.application.usecase.addgame.exception.PotNotMatchRuntimeException;
import com.slusarz.pokerafterdark.application.usecase.addgame.validator.AddGameValidator;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameParticipant;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;

public class AddGameValidatorStepdefs {

    private AddGameValidator addGameValidator;

    private ExceptionsHandler exceptionsHandler;

    private List<CashGameParticipant> cashGameParticipants = new ArrayList<>();

    @Before
    public void setUp() {
        GameRepository gameRepository = mock(GameRepository.class);
        addGameValidator = new AddGameValidator(gameRepository);
        exceptionsHandler = new ExceptionsHandler();
        cashGameParticipants = new ArrayList<>();
    }

    @Given("^Participants with pot sum to zero$")
    public void participiantsWithPotSumTo() {
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(-20)));
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(0)));
    }

    @When("^Validate participations$")
    public void validateParticipations() {
        try {
            addGameValidator.validatePot(cashGameParticipants.stream().map(CashGameParticipant::getEarnings).collect(Collectors.toList()));
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
    }

    @Then("^Validation pass$")
    public void validationPass() {
        Assert.assertEquals(0, exceptionsHandler.size(PotNotMatchRuntimeException.class));
    }

    @Given("^Participants with pot not sum zero$")
    public void participantsWithPotNotSum() {
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(0)));

    }

    @Then("^Validation failed$")
    public void validationFailed() {
        Assert.assertEquals(1, exceptionsHandler.size(PotNotMatchRuntimeException.class));
    }
}
