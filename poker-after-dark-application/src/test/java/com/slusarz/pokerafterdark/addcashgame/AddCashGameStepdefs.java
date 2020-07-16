package com.slusarz.pokerafterdark.addcashgame;

import com.slusarz.pokerafterdark.CountUtil;
import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.TestCommandExecutor;
import com.slusarz.pokerafterdark.TimeUtil;
import com.slusarz.pokerafterdark.application.common.events.EventBus;
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

    private TestCommandExecutor testCommandExecutor;

    @Before
    public void setUp() {
        gameRepository = mock(GameRepository.class);
        addGameValidator = new AddGameValidator(gameRepository);
        cashGameRepository = new MockCashGameRepository();
        exceptionsHandler = new ExceptionsHandler();
        eventBus = mock(EventBus.class);
        testCommandExecutor = new TestCommandExecutor(exceptionsHandler);
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
        addCashGameCommand = AddCashGameCommand.of(PlayerId.of("PLAYER_1"), LocalDate.now(), pot, cashGameParticipants, skipPotValidation);
        AddCashGameCommandHandler addCashGameCommandHandler = new AddCashGameCommandHandler(eventBus, cashGameRepository, addGameValidator);
        addCashGameCommandResult = testCommandExecutor.execute(addCashGameCommandHandler, addCashGameCommand);
    }

    @Then("^Game was not added$")
    public void gameWasNotAdded() {
        Assert.assertNull(addCashGameCommandResult);
        Assert.assertTrue(exceptionsHandler.isNotEmpty());
    }

    @Then("^Game was added$")
    public void gameWasAdded() {
        Assert.assertNotNull(addCashGameCommandResult);
        Assert.assertTrue(exceptionsHandler.isEmpty());
    }

    @And("^Add game event was not emitted$")
    public void addGameEventWasNotEmitted() {
        verify(eventBus, times(CountUtil.ZERO)).fireEvent(any());
    }

    @And("^Add game event was emitted$")
    public void addGameEventWasEmitted() {
        verify(eventBus, times(CountUtil.ONCE)).fireEvent(any(AddCashGameEvent.class));
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
