package com.slusarz.pokerafterdark.infrastructure.player;

import com.slusarz.pokerafterdark.application.player.PlayerProjection;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.NumberOfPlays;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.infrastructure.TestJpaConfiguration;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.player.PlayerQueryJpaRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestJpaConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
public class PlayerQueryJpaRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private PlayerQueryJpaRepository playerQueryJpaRepository;

    @Before
    public void init() {
        playerQueryJpaRepository = new PlayerQueryJpaRepository(entityManager, new PlayerEntityMapper());
    }

    @Test
    @Transactional
    @Sql("classpath:read_players.sql")
    public void shouldReadPlayer() {
        PlayerProjection expectedPlayer
                = PlayerProjection.of(PlayerId.of("PLAYER_3"),
                PlayerName.of("PLAYER_NAME_3"),
                Earnings.of(-110),
                Earnings.of(10),
                Earnings.of(-60),
                NumberOfPlays.of(5));

        PlayerProjection playerProjection = playerQueryJpaRepository.read(expectedPlayer.getPlayerId());

        assertPlayer(expectedPlayer, playerProjection);
    }

    private static void assertPlayer(PlayerProjection expected, PlayerProjection actual) {
        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getPlayerId(), actual.getPlayerId());
        Assert.assertEquals(expected.getLiveEarnings(), actual.getLiveEarnings());
        Assert.assertEquals(expected.getNumberOfPlays(), actual.getNumberOfPlays());
        Assert.assertEquals(expected.getPlayerName(), actual.getPlayerName());
        Assert.assertEquals(expected.getBiggestLose(), actual.getBiggestLose());
        Assert.assertEquals(expected.getBiggestWin(), actual.getBiggestWin());
        Assert.assertEquals(expected, actual);

    }

}
