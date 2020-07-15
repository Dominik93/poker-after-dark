package com.slusarz.pokerafterdark.infrastructure.player;

import com.slusarz.pokerafterdark.application.player.PlayerProjection;
import com.slusarz.pokerafterdark.domain.player.NumberOfPlays;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.TestJpaConfiguration;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.players.PlayersQueryJpaRepository;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestJpaConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
public class PlayersQueryJpaRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private PlayersQueryJpaRepository playersQueryJpaRepository;

    @Before
    public void init() {
        playersQueryJpaRepository = new PlayersQueryJpaRepository(entityManager, new PlayerEntityMapper());
    }

    @Test
    @Transactional
    @Sql("classpath:read_players.sql")
    public void shouldReadPlayers() {
        List<PlayerProjection> playerProjections = playersQueryJpaRepository.readPlayers();

        Assert.assertFalse(playerProjections.isEmpty());
        Assert.assertEquals(5, playerProjections.size());

        Assert.assertEquals(PlayerId.of("PLAYER_1"), playerProjections.get(0).getPlayerId());
        Assert.assertEquals(PlayerId.of("PLAYER_3"), playerProjections.get(1).getPlayerId());
        Assert.assertEquals(PlayerId.of("PLAYER_2"), playerProjections.get(2).getPlayerId());
        Assert.assertEquals(PlayerId.of("PLAYER_5"), playerProjections.get(3).getPlayerId());
        Assert.assertEquals(PlayerId.of("PLAYER_4"), playerProjections.get(4).getPlayerId());

        Assert.assertEquals(NumberOfPlays.of(5), playerProjections.get(0).getNumberOfPlays());
        Assert.assertEquals(NumberOfPlays.of(5), playerProjections.get(1).getNumberOfPlays());
        Assert.assertEquals(NumberOfPlays.of(4), playerProjections.get(2).getNumberOfPlays());
        Assert.assertEquals(NumberOfPlays.of(2), playerProjections.get(3).getNumberOfPlays());
        Assert.assertEquals(NumberOfPlays.of(0), playerProjections.get(4).getNumberOfPlays());
    }

}
