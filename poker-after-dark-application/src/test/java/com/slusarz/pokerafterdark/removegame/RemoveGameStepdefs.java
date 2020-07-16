package com.slusarz.pokerafterdark.removegame;

import com.slusarz.pokerafterdark.CountUtil;
import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.TestCommandExecutor;
import com.slusarz.pokerafterdark.application.common.events.EventBus;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommand;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommandResult;
import com.slusarz.pokerafterdark.application.usecase.removegame.event.RemoveGameEvent;
import com.slusarz.pokerafterdark.application.usecase.removegame.validator.RemoveGameValidator;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import com.slusarz.pokerafterdark.game.MockGameRepository;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RemoveGameStepdefs {

    private GameRepository gameRepository;

    private RemoveGameCommandResult removeGameCommandResult;

    private RemoveGameCommand removeGameCommand;

    private EventBus eventBus;

    private ExceptionsHandler exceptionsHandler;

    private RemoveGameValidator removeGameValidator;

    private TestCommandExecutor testCommandExecutor;

    @Before
    public void setUp() {
        exceptionsHandler = new ExceptionsHandler();
        eventBus = mock(EventBus.class);
        gameRepository = new MockGameRepository();
        removeGameValidator = new RemoveGameValidator(gameRepository);
        testCommandExecutor = new TestCommandExecutor(exceptionsHandler);
    }

    @Given("^Remove game command with existing id$")
    public void removeGameCommandWithExistingId() {
        removeGameCommand = RemoveGameCommand.of(GameId.of("GAME_1"));
    }

    @When("^Invoke remove game handler$")
    public void invokeRemoveGameHandler() {
        RemoveGameCommandHandler removeGameCommandHandler = new RemoveGameCommandHandler(eventBus, gameRepository, removeGameValidator);
        removeGameCommandResult = testCommandExecutor.execute(removeGameCommandHandler, removeGameCommand);
    }

    @Then("^Game was removed$")
    public void gameWasRemoved() {
        Assert.assertNotNull(removeGameCommandResult);
    }

    @And("^Game removed event was emitted$")
    public void gameRemovedEventWasEmitted() {
        verify(eventBus, times(CountUtil.ONCE)).fireEvent(any(RemoveGameEvent.class));
    }

}
