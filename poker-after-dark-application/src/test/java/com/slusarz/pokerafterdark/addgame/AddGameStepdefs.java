package com.slusarz.pokerafterdark.addgame;

import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.application.events.EventBus;
import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameCommand;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameCommandResult;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameValidator;
import com.slusarz.pokerafterdark.application.usecase.addgame.GameRepository;
import com.slusarz.pokerafterdark.application.usecase.addgame.event.AddGameEvent;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.Pot;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.game.MockGameRepository;
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

public class AddGameStepdefs {

    private GameRepository gameRepository;
    private EventBus eventBus;
    private AddGameValidator addGameValidator;
    private AddGameCommandResult addGameCommandResult;
    private GameQueryRepository gameQueryRepository;
    private ExceptionsHandler exceptionsHandler;
    private AddGameCommand addGameCommand;

    private boolean skipPotValidation;
    private List<Participant> participants;
    private Pot pot;


    @Before
    public void setUp() {
        gameQueryRepository = mock(GameQueryRepository.class);
        addGameValidator = new AddGameValidator(gameQueryRepository);
        gameRepository = new MockGameRepository();
        exceptionsHandler = new ExceptionsHandler();
        eventBus = mock(EventBus.class);
    }

    @And("^With skip validation set to \"([^\"]*)\"$")
    public void withSkipValidationSetTo(String booleanValue) {
        skipPotValidation = Boolean.valueOf(booleanValue);
    }

    @And("^Pot not matches earnings$")
    public void potNotMatchesEarnings() {
        participants = new ArrayList<>();
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(80)));
        participants.add(Participant.of(PlayerId.of("PLAYER_2"), Earnings.of(-20)));
        pot = Pot.of(20);
    }

    @And("^Pot matches earnings$")
    public void potMatchesEarnings() {
        participants = new ArrayList<>();
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_2"), Earnings.of(-20)));
        pot = Pot.of(20);
    }

    @When("^Invoke add game handler$")
    public void invokeAddGameHandler() {
        try {
            addGameCommand = AddGameCommand.of(PlayerId.of("PLAYER_1"), LocalDate.now(), pot, participants, skipPotValidation);
            addGameCommandResult = new AddGameCommandHandler(eventBus, gameRepository, addGameValidator)
                    .handle(addGameCommand);
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
    }

    @Then("^Game was not added$")
    public void gameWasNotAdded() {
        Assert.assertNull(addGameCommandResult);
        Assert.assertTrue(exceptionsHandler.size() > 0);
    }

    @Then("^Game was added$")
    public void gameWasAdded() {
        Assert.assertNotNull(addGameCommandResult);
        Assert.assertEquals(0, exceptionsHandler.size());
    }

    @And("^Add game event was not emitted$")
    public void addGameEventWasNotEmitted() {
        verify(eventBus, times(0)).fireEvent(any());
    }

    @And("^Add game event was emitted$")
    public void addGameEventWasEmitted() {
        verify(eventBus, times(1)).fireEvent(any(AddGameEvent.class));
    }

    @Given("^Add game command$")
    public void addGameCommand() {
        addGameCommand = null;
    }

    @And("^With date after last game$")
    public void withDateAfterLastGame() {
        Game game = mock(Game.class);
        when(game.getDate()).thenReturn(LocalDate.now().minusDays(1));
        when(gameQueryRepository.readLast()).thenReturn(game);
    }

    @And("^With date before last game$")
    public void withDateBeforeLastGame() {
        Game game = mock(Game.class);
        when(game.getDate()).thenReturn(LocalDate.now().plusDays(1));
        when(gameQueryRepository.readLast()).thenReturn(game);
    }
}
