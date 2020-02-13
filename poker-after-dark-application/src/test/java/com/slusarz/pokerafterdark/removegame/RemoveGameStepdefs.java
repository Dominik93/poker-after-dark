package com.slusarz.pokerafterdark.removegame;

import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.application.events.EventBus;
import com.slusarz.pokerafterdark.application.usecase.addgame.GameRepository;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommand;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommandResult;
import com.slusarz.pokerafterdark.application.usecase.removegame.event.RemoveGameEvent;
import com.slusarz.pokerafterdark.domain.game.GameId;
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

    @Before
    public void setUp() {
        exceptionsHandler = new ExceptionsHandler();
        eventBus = mock(EventBus.class);
        gameRepository = new MockGameRepository();
    }

    @Given("^Remove game command with existing id$")
    public void removeGameCommandWithExistingId() {
        removeGameCommand = RemoveGameCommand.of(GameId.of("GAME_1"));
    }

    @When("^Invoke remove game handler$")
    public void invokeRemoveGameHandler() {
        try {
            removeGameCommandResult = new RemoveGameCommandHandler(eventBus, gameRepository).handle(removeGameCommand);
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
    }

    @Then("^Game was removed$")
    public void gameWasRemoved() {
        Assert.assertNotNull(removeGameCommandResult);
    }

    @And("^Game removed event was emitted$")
    public void gameRemovedEventWasEmitted() {
        verify(eventBus, times(1)).fireEvent(any(RemoveGameEvent.class));
    }

}
