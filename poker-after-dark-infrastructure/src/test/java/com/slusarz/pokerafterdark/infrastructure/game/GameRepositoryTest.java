package com.slusarz.pokerafterdark.infrastructure.game;

import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.Pot;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.TestJpaConfiguration;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.ParticipationJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.ProfitJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ParticipationEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameJpaRepository;
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
public class GameRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private GameJpaRepository gameJpaRepository;

    @Before
    public void init() {
        ParticipationEntityMapper participationEntityMapper = new ParticipationEntityMapper();
        GameEntityMapper gameEntityMapper = new GameEntityMapper(participationEntityMapper);
        gameJpaRepository = new GameJpaRepository(entityManager, gameEntityMapper, participationEntityMapper);
    }

    @Test
    @Transactional
    @Sql("classpath:save_game.sql")
    public void shouldSaveGame() {
        List<Participant> participants = new ArrayList<>();
        participants.add(Participant.of(PlayerId.of("PLAYER_2"), Earnings.of(-10)));
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(10)));
        GameId gameId = GameId.of("GAME_6");
        PlayerId host = PlayerId.of("PLAYER_1");
        Game game = Game.of(gameId, host, LocalDate.now(), Pot.of(20), participants);

        gameJpaRepository.save(game);

        GameJpaEntity gameJpaEntity = entityManager.find(GameJpaEntity.class, gameId.getId());
        Assert.assertNotNull(gameJpaEntity);
        Assert.assertEquals(2, gameJpaEntity.getParticipations().size());
    }

    @Test
    @Transactional
    @Sql("classpath:save_game.sql")
    public void shouldRemoveGame() {
        GameId gameId = GameId.of("GAME_5");
        GameJpaEntity gameJpaEntity = entityManager.find(GameJpaEntity.class, gameId.getId());

        gameJpaRepository.remove(gameId);

        Assert.assertNull(entityManager.find(GameJpaEntity.class, gameId.getId()));
        for (ParticipationJpaEntity participation : gameJpaEntity.getParticipations()) {
            Assert.assertNull(entityManager.find(ParticipationJpaEntity.class, participation.getId()));
        }
        for (ProfitJpaEntity profit : gameJpaEntity.getProfits()) {
            Assert.assertNull(entityManager.find(ProfitJpaEntity.class, profit.getId()));
        }
    }
}
