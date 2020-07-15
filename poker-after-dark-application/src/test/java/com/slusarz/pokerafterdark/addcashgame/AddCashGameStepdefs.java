package com.slusarz.pokerafterdark.addcashgame;

import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.TimeUtil;
import com.slusarz.pokerafterdark.application.events.EventBus;
import com.slusarz.pokerafterdark.application.usecase.addcashgame.AddCashGameCommand;
import com.slusarz.pokerafterdark.application.usecase.addcashgame.AddCashGameCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.addcashgame.AddCashGameCommandResult;
import com.slusarz.pokerafterdark.application.usecase.addcashgame.event.AddCashGameEvent;
import com.slusarz.pokerafterdark.application.usecase.addgame.validator.AddGameValidator;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameParticipant;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameRepository;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.game.MockCashGameRepository;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddCashGameStepdefs {

    private CashGameRepository cashGameRepository;

    private EventBus eventBus;

    private AddGameValidator addGameValidator;

    private AddCashGameCommandResult addCashGameCommandResult;

    private GameRepository gameRepository;

    private ExceptionsHandler exceptionsHandler;

    private AddCashGameCommand addCashGameCommand;

    private boolean skipPotValidation;

    private List<CashGameParticipant> cashGameParticipants;

    private Pot pot;

    @Before
    public void setUp() {
        gameRepository = mock(GameRepository.class);
        addGameValidator = new AddGameValidator(gameRepository);
        cashGameRepository = new MockCashGameRepository();
        exceptionsHandler = new ExceptionsHandler();
        eventBus = mock(EventBus.class);
    }

    @And("^With skip validation set to \"([^\"]*)\"$")
    public void withSkipValidationSetTo(String booleanValue) {
        skipPotValidation = Boolean.valueOf(booleanValue);
    }

    @And("^Pot not matches earnings$")
    public void potNotMatchesEarnings() {
        cashGameParticipants = new ArrayList<>();
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(80)));
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_2"), Earnings.of(-20)));
        pot = Pot.of(20);
    }

    @And("^Pot matches earnings$")
    public void potMatchesEarnings() {
        cashGameParticipants = new ArrayList<>();
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_2"), Earnings.of(-20)));
        pot = Pot.of(20);
    }

    @When("^Invoke add game handler$")
    public void invokeAddGameHandler() {
        try {
            addCashGameCommand = AddCashGameCommand.of(PlayerId.of("PLAYER_1"), LocalDate.now(), pot, cashGameParticipants, skipPotValidation);
            addCashGameCommandResult = new AddCashGameCommandHandler(eventBus, cashGameRepository, addGameValidator)
                    .handle(addCashGameCommand);
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
    }

    @Then("^Game was not added$")
    public void gameWasNotAdded() {
        Assert.assertNull(addCashGameCommandResult);
        Assert.assertTrue(exceptionsHandler.size() > 0);
    }

    @Then("^Game was added$")
    public void gameWasAdded() {
        Assert.assertNotNull(addCashGameCommandResult);
        Assert.assertEquals(0, exceptionsHandler.size());
    }

    @And("^Add game event was not emitted$")
    public void addGameEventWasNotEmitted() {
        verify(eventBus, times(0)).fireEvent(any());
    }

    @And("^Add game event was emitted$")
    public void addGameEventWasEmitted() {
        verify(eventBus, times(1)).fireEvent(any(AddCashGameEvent.class));
    }

    @Given("^Add game command$")
    public void addGameCommand() {
        addCashGameCommand = null;
    }

    @And("^With date \"([^\"]*)\" last game$")
    public void withDateLastGame(String time) {
        Game game = mock(Game.class);
        if (TimeUtil.isAfter(time)) {
            when(game.getOccurrenceDate()).thenReturn(LocalDate.now().minusDays(1));
        } else {
            when(game.getOccurrenceDate()).thenReturn(LocalDate.now().plusDays(1));
        }
        when(gameRepository.readLast()).thenReturn(game);
    }
}
