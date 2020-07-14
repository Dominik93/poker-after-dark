package com.slusarz.pokerafterdark.addtournament;

import com.slusarz.pokerafterdark.BooleanUtil;
import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.application.events.EventBus;
import com.slusarz.pokerafterdark.application.usecase.addgame.validator.AddGameValidator;
import com.slusarz.pokerafterdark.application.usecase.addtournament.AddTournamentCommand;
import com.slusarz.pokerafterdark.application.usecase.addtournament.AddTournamentCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.addtournament.event.AddTournamentEvent;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.domain.tournament.Place;
import com.slusarz.pokerafterdark.domain.tournament.TournamentId;
import com.slusarz.pokerafterdark.domain.tournament.TournamentParticipant;
import com.slusarz.pokerafterdark.domain.tournament.TournamentRepository;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddTournamentStepdefs {

    private Pot pot;

    private List<TournamentParticipant> tournamentParticipants;

    private EventBus eventBus;

    private ExceptionsHandler exceptionsHandler;

    private GameRepository gameRepository;

    private AddGameValidator addGameValidator;

    private TournamentRepository tournamentRepository;

    @Before
    public void setUp() {
        tournamentRepository = Mockito.mock(TournamentRepository.class);
        when(tournamentRepository.generateId()).thenReturn(TournamentId.of(UUID.randomUUID().toString()));
        gameRepository = Mockito.mock(GameRepository.class);
        addGameValidator = new AddGameValidator(gameRepository);
        exceptionsHandler = new ExceptionsHandler();
        eventBus = mock(EventBus.class);
    }

    @Given("^Add tournament command$")
    public void addTournamentCommand() {
    }

    @And("^With tournament date \"([^\"]*)\" last game$")
    public void withDateLastGame(String time) {
        Game game = mock(Game.class);
        if ("after".equals(time)) {
            when(game.getOccurrenceDate()).thenReturn(LocalDate.now().minusDays(1));
        } else {
            when(game.getOccurrenceDate()).thenReturn(LocalDate.now().plusDays(1));
        }
        when(gameRepository.readLast()).thenReturn(game);
    }

    @And("^With pot \"([^\"]*)\" earnings$")
    public void withPotEarnings(String matches) throws Throwable {
        if (BooleanUtil.convertString(matches)) {
            pot = Pot.of(50);
            tournamentParticipants = Arrays.asList(
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(-10 + 50 * 0.7), Place.of(1)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(-10 + 50 * 0.3), Place.of(2)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(-10), Place.of(3)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(-10), Place.of(4)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(-10), Place.of(5))
            );
        } else {
            pot = Pot.of(50);
            tournamentParticipants = Arrays.asList(
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(50 * 0.7), Place.of(1)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(50 * 0.3), Place.of(2)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(50 * 0.1), Place.of(3)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(0), Place.of(4)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(0), Place.of(5))
            );
        }
    }

    @When("^Invoke add tournament command handler$")
    public void invokeAddTournamentCommandHandler() {
        AddTournamentCommand addTournamentCommand = AddTournamentCommand.of(
                PlayerId.of("PLAYER_ID_1"),
                LocalDate.now(),
                pot,
                tournamentParticipants);
        try {
            new AddTournamentCommandHandler(eventBus, tournamentRepository, addGameValidator)
                    .handle(addTournamentCommand);
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
    }


    @Then("^Tournament was \"([^\"]*)\"$")
    public void tournamentWas(String wasAdded) throws Throwable {
        if (BooleanUtil.convertString(wasAdded)) {
            Assert.assertEquals(0, exceptionsHandler.size());
            Mockito.verify(tournamentRepository, times(1)).save(any());
        } else {
            Assert.assertTrue(exceptionsHandler.size() > 0);
            Mockito.verify(tournamentRepository, times(0)).save(any());
        }
    }

    @And("^Add tournament event was \"([^\"]*)\".$")
    public void addTournamentEventWas(String wasEmitted) {
        if (BooleanUtil.convertString(wasEmitted)) {
            verify(eventBus, times(1)).fireEvent(any(AddTournamentEvent.class));
        } else {
            verify(eventBus, times(0)).fireEvent(any());
        }
    }

}
