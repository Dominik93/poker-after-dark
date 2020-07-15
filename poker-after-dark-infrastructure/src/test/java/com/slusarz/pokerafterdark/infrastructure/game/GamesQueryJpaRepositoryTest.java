package com.slusarz.pokerafterdark.infrastructure.game;

import com.slusarz.pokerafterdark.application.game.GameProjection;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.infrastructure.TestJpaConfiguration;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameProjectionEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameCaller;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameQueryCaller;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameQueryJpaRepository;
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
import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestJpaConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
public class GamesQueryJpaRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private GameQueryJpaRepository gameQueryJpaRepository;

    @Before
    public void init() {
        GameProjectionEntityMapper gameEntityMapper = new GameProjectionEntityMapper();
        GameQueryCaller gameQueryCaller = new GameQueryCaller(entityManager);
        GameCaller gameCaller = new GameCaller(entityManager);
        gameQueryJpaRepository = new GameQueryJpaRepository(entityManager, gameQueryCaller, gameCaller, gameEntityMapper);
    }

    @Test
    @Transactional
    @Sql("classpath:read_games.sql")
    public void shouldReadGames() {
        LocalDate from = LocalDate.of(2018, 9, 22);
        LocalDate to = LocalDate.of(2019, 8, 30);
        List<GameProjection> gameProjections = gameQueryJpaRepository.read(from, to, Collections.emptyList());

        Assert.assertEquals(3, gameProjections.size());
        Assert.assertTrue(gameProjections.stream()
                .anyMatch(gameProjection -> gameProjection.getGameId().equals(GameId.of("GAME_3"))));
        Assert.assertTrue(gameProjections.stream()
                .anyMatch(gameProjection -> gameProjection.getGameId().equals(GameId.of("GAME_4"))));
        Assert.assertTrue(gameProjections.stream()
                .anyMatch(gameProjection -> gameProjection.getGameId().equals(GameId.of("GAME_5"))));
    }
}
