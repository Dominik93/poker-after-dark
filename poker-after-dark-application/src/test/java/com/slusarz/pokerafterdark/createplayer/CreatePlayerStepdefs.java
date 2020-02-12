package com.slusarz.pokerafterdark.createplayer;

import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.application.events.EventBus;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommand;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommandResult;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerValidator;
import com.slusarz.pokerafterdark.application.usecase.createplayer.PlayerRepository;
import com.slusarz.pokerafterdark.application.usecase.createplayer.event.PlayerCreatedEvent;
import com.slusarz.pokerafterdark.application.usecase.createplayer.exceptions.PlayerNameExistException;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.player.MockPlayerRepository;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreatePlayerStepdefs {

    private PlayerRepository playerRepository;
    private CreatePlayerCommandResult createPlayerCommandResult;
    private CreatePlayerCommand createPlayerCommand;
    private CreatePlayerValidator createPlayerValidator;
    private EventBus eventBus;
    private ExceptionsHandler exceptionsHandler;

    @Before
    public void setUp() {
        exceptionsHandler = new ExceptionsHandler();
        eventBus = mock(EventBus.class);
        playerRepository = new MockPlayerRepository();
        createPlayerValidator = new CreatePlayerValidator(playerRepository);
    }

    @Given("^Create player command with unique name$")
    public void createPlayerCommandWithUniqueName() {
        createPlayerCommand = CreatePlayerCommand.of(PlayerName.of("UNIQUE"));
    }

    @When("^Invoke create player handler$")
    public void invokeCreatePlayerHandler() {
        try {
            createPlayerCommandResult = new CreatePlayerCommandHandler(playerRepository, createPlayerValidator, eventBus)
                    .handle(createPlayerCommand);
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
    }

    @Then("^New player was created$")
    public void newPlayerWasCreated() {
        int size = playerRepository.readPlayers(Collections.singleton(createPlayerCommandResult.getPlayerId())).size();
        Assert.assertEquals(1, size);
    }

    @Given("^Create player command with existing name$")
    public void createPlayerCommandWithExistingName() {
        createPlayerCommand = CreatePlayerCommand.of(PlayerName.of("EXISTING_NAME"));
    }

    @Then("^New player was not created$")
    public void newPlayerWasNotCreated() {
        Assert.assertEquals(1, exceptionsHandler.get(PlayerNameExistException.class).size());
        Assert.assertNull(createPlayerCommandResult);
    }

    @And("^Player created event was emitted$")
    public void playerCreatedEventWasEmitted() {
        verify(eventBus, times(1)).fireEvent(any(PlayerCreatedEvent.class));
    }

    @And("^Player created event was not emitted$")
    public void playerCreatedEventWasNotEmitted() {
        verify(eventBus, times(0)).fireEvent(any());
    }
}
