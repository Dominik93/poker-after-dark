package com.slusarz.pokerafterdark.addtournament;

import com.slusarz.pokerafterdark.BooleanUtil;
import com.slusarz.pokerafterdark.CountUtil;
import com.slusarz.pokerafterdark.ExceptionsHandler;
import com.slusarz.pokerafterdark.TestCommandExecutor;
import com.slusarz.pokerafterdark.TimeUtil;
import com.slusarz.pokerafterdark.application.common.events.EventBus;
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
import java.util.ArrayList;
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

    private AddTournamentCommand addTournamentCommand;

    private TestCommandExecutor testCommandExecutor;

    @Before
    public void setUp() {
        tournamentParticipants = new ArrayList<>();
        pot = Pot.of(50);
        tournamentRepository = Mockito.mock(TournamentRepository.class);
        when(tournamentRepository.generateId()).thenReturn(TournamentId.of(UUID.randomUUID().toString()));
        gameRepository = Mockito.mock(GameRepository.class);
        addGameValidator = new AddGameValidator(gameRepository);
        exceptionsHandler = new ExceptionsHandler();
        eventBus = mock(EventBus.class);
        testCommandExecutor = new TestCommandExecutor(exceptionsHandler);
    }

    @Given("^Operator fill tournament form$")
    public void addTournamentCommand() {
        PlayerId host = PlayerId.of("PLAYER_ID_1");
        LocalDate occurrenceDate = LocalDate.now();
        addTournamentCommand = AddTournamentCommand.of(host, occurrenceDate, pot, tournamentParticipants);
    }

    @And("^With tournament date \"([^\"]*)\" last game$")
    public void withDateLastGame(String time) {
        Game game = mock(Game.class);
        if (TimeUtil.isAfter(time)) {
            when(game.getOccurrenceDate()).thenReturn(LocalDate.now().minusDays(1));
        } else {
            when(game.getOccurrenceDate()).thenReturn(LocalDate.now().plusDays(1));
        }
        when(gameRepository.readLast()).thenReturn(game);
    }

    @And("^With pot \"([^\"]*)\" matches earnings$")
    public void withPotMatchesEarnings(String matches) {
        if (BooleanUtil.isTrue(matches)) {
            tournamentParticipants.addAll(Arrays.asList(
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(-10 + 50 * 0.7), Place.of(1)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_2"), Earnings.of(-10 + 50 * 0.3), Place.of(2)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_3"), Earnings.of(-10), Place.of(3)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_4"), Earnings.of(-10), Place.of(4)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_5"), Earnings.of(-10), Place.of(5))
            ));
        } else {
            tournamentParticipants.addAll(Arrays.asList(
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_1"), Earnings.of(50 * 0.7), Place.of(1)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_2"), Earnings.of(50 * 0.3), Place.of(2)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_3"), Earnings.of(50 * 0.1), Place.of(3)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_4"), Earnings.of(0), Place.of(4)),
                    TournamentParticipant.of(PlayerId.of("PLAYER_ID_5"), Earnings.of(0), Place.of(5))
            ));
        }
    }

    @When("^Administrator save tournament$")
    public void administratorSaveTournament() {
        AddTournamentCommandHandler addTournamentCommandHandler
                = new AddTournamentCommandHandler(eventBus, tournamentRepository, addGameValidator);
        testCommandExecutor.execute(addTournamentCommandHandler, addTournamentCommand);
    }

    @Then("^Tournament \"([^\"]*)\" added$")
    public void tournamentWas(String wasAdded) {
        if (BooleanUtil.isTrue(wasAdded)) {
            Assert.assertTrue(exceptionsHandler.isEmpty());
            verify(eventBus, times(CountUtil.ONCE)).fireEvent(any(AddTournamentEvent.class));
            Mockito.verify(tournamentRepository, times(CountUtil.ONCE)).save(any());
        } else {
            Assert.assertTrue(exceptionsHandler.isNotEmpty());
            Mockito.verify(tournamentRepository, times(CountUtil.ZERO)).save(any());
            verify(eventBus, times(CountUtil.ZERO)).fireEvent(any());
        }
    }

}
