package com.slusarz.pokerafterdark.infrastructure.tournament;

import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.domain.tournament.Place;
import com.slusarz.pokerafterdark.domain.tournament.Tournament;
import com.slusarz.pokerafterdark.domain.tournament.TournamentId;
import com.slusarz.pokerafterdark.domain.tournament.TournamentParticipant;
import com.slusarz.pokerafterdark.infrastructure.TestJpaConfiguration;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ParticipationEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.TournamentEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.tournament.TournamentJpaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestJpaConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
public class TournamentJpaRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private TournamentJpaRepository tournamentJpaRepository;

    @Before
    public void init() {
        ParticipationEntityMapper participationEntityMapper = new ParticipationEntityMapper();
        TournamentEntityMapper tournamentEntityMapper = new TournamentEntityMapper();
        tournamentJpaRepository = new TournamentJpaRepository(entityManager, tournamentEntityMapper, participationEntityMapper);
    }

    @Test
    @Transactional
    @Sql("classpath:save_game.sql")
    public void shouldSaveTournament() {
        TournamentId tournamentId = TournamentId.of("NEW_TOURNAMENT_ID");
        PlayerId playerId = PlayerId.of("PLAYER_1");
        List<TournamentParticipant> tournamentParticipants = new ArrayList<>();
        tournamentParticipants.add(TournamentParticipant.of(PlayerId.of("PLAYER_2"), Earnings.of(-10), Place.of(1)));
        tournamentParticipants.add(TournamentParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(10), Place.of(2)));
        LocalDate occurrenceDate = LocalDate.now();
        Pot pot = Pot.of(20);
        Tournament tournament = Tournament.of(tournamentId, tournamentParticipants, playerId, occurrenceDate, pot);

        tournamentJpaRepository.save(tournament);

        GameJpaEntity gameJpaEntity = entityManager.find(GameJpaEntity.class, tournamentId.getId());

        Assert.assertEquals(tournament.getTournamentId().getId(), gameJpaEntity.getId());
        Assert.assertEquals(tournament.getHost().getId(), gameJpaEntity.getHost().getId());
        Assert.assertEquals(tournament.getOccurrenceDate(), gameJpaEntity.getOccurrenceDate());
        Assert.assertEquals(Integer.valueOf(tournament.getPot().getValue()), gameJpaEntity.getPot());

    }

}
