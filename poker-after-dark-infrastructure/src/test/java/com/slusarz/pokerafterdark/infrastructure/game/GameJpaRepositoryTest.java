package com.slusarz.pokerafterdark.infrastructure.game;

import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.infrastructure.TestJpaConfiguration;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.participation.ParticipationJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.profit.ProfitJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameCaller;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestJpaConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
public class GameJpaRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private GameJpaRepository gameJpaRepository;

    @Before
    public void init() {
        GameCaller gameCaller = new GameCaller(entityManager);
        gameJpaRepository = new GameJpaRepository(entityManager, gameCaller, new GameEntityMapper());
    }

    @Test
    @Transactional
    @Sql("classpath:save_game.sql")
    public void shouldRemoveGame() {
        GameId cashGameId = GameId.of("GAME_5");
        GameJpaEntity gameJpaEntity = entityManager.find(GameJpaEntity.class, cashGameId.getId());

        gameJpaRepository.remove(cashGameId);

        Assert.assertNull(entityManager.find(GameJpaEntity.class, cashGameId.getId()));
        for (ParticipationJpaEntity participation : gameJpaEntity.getParticipations()) {
            Assert.assertNull(entityManager.find(ParticipationJpaEntity.class, participation.getId()));
        }
        for (ProfitJpaEntity profit : gameJpaEntity.getProfits()) {
            Assert.assertNull(entityManager.find(ProfitJpaEntity.class, profit.getId()));
        }
    }


    @Test
    @Transactional
    @Sql("classpath:read_games.sql")
    public void shouldReadLastGame() {
        Game game = gameJpaRepository.readLast();

        Assert.assertEquals(LocalDate.of(2019, 8, 30), game.getOccurrenceDate());
    }
}
