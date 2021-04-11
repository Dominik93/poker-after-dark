package com.slusarz.pokerafterdark.createplayer;

import com.slusarz.pokerafterdark.BooleanUtil;
import com.slusarz.pokerafterdark.CountUtil;
import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.TestCommandExecutor;
import com.slusarz.pokerafterdark.application.common.events.EventBus;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommand;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommandResult;
import com.slusarz.pokerafterdark.application.usecase.createplayer.event.PlayerCreatedEvent;
import com.slusarz.pokerafterdark.application.usecase.createplayer.exceptions.PlayerNameExistException;
import com.slusarz.pokerafterdark.application.usecase.createplayer.validator.CreatePlayerValidator;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.player.PlayerRepository;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreatePlayerStepdefs {

    private PlayerRepository playerRepository;
    private CreatePlayerCommandResult createPlayerCommandResult;
    private CreatePlayerCommand createPlayerCommand;
    private CreatePlayerValidator createPlayerValidator;
    private EventBus eventBus;
    private ExceptionsHandler exceptionsHandler;
    private TestCommandExecutor testCommandExecutor;

    @Before
    public void setUp() {
        exceptionsHandler = new ExceptionsHandler();
        eventBus = mock(EventBus.class);
        playerRepository = Mockito.mock(PlayerRepository.class);
        when(playerRepository.generateId()).thenReturn(PlayerId.of("PLAYER_ID"));
        createPlayerValidator = new CreatePlayerValidator(playerRepository);
        testCommandExecutor = new TestCommandExecutor(exceptionsHandler);
    }

    @Given("^Administrator type player name$")
    public void administratorTypePlayerName() {
        createPlayerCommand = CreatePlayerCommand.of(PlayerName.of("PLAYER_NAME"));
    }

    @And("^Player name \"([^\"]*)\"$")
    public void playerName(String isUnique) {
        boolean playerExist = !BooleanUtil.isTrue(isUnique);
        when(playerRepository.playerExist(any())).thenReturn(playerExist);
    }

    @When("^Administrator save player$")
    public void administratorSavePlayer() {
        try {
            CreatePlayerCommandHandler createPlayerCommandHandler = new CreatePlayerCommandHandler(playerRepository, createPlayerValidator, eventBus);
            createPlayerCommandResult = testCommandExecutor.execute(createPlayerCommandHandler, createPlayerCommand);
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
    }

    @Then("^Player \"([^\"]*)\"$")
    public void player(String wasSaved) {
        if (BooleanUtil.isTrue(wasSaved)) {
            Assert.assertTrue(exceptionsHandler.isEmpty());
            Assert.assertNotNull(createPlayerCommandResult);
            verify(playerRepository, times(CountUtil.ONCE)).save(any(Player.class));
            verify(eventBus, times(CountUtil.ONCE)).fireEvent(any(PlayerCreatedEvent.class));
        } else {
            Assert.assertEquals(CountUtil.ONCE, exceptionsHandler.get(PlayerNameExistException.class).size());
            Assert.assertNull(createPlayerCommandResult);
            verify(playerRepository, times(CountUtil.ZERO)).save(any(Player.class));
            verify(eventBus, times(CountUtil.ZERO)).fireEvent(any());
        }
    }
}
