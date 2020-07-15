package com.slusarz.pokerafterdark.infrastructure.player;

import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.NumberOfPlays;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.infrastructure.TestJpaConfiguration;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.player.PlayerJpaRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestJpaConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
public class PlayerJpaRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private PlayerJpaRepository playerJpaRepository;

    @Before
    public void init() {
        playerJpaRepository = new PlayerJpaRepository(entityManager, new PlayerEntityMapper());
    }

    @Test
    @Transactional
    @Sql("classpath:read_players.sql")
    public void shouldReadPlayers() {
        Set<PlayerId> playerIds = new HashSet<>();
        playerIds.add(PlayerId.of("PLAYER_1"));
        playerIds.add(PlayerId.of("PLAYER_2"));
        playerIds.add(PlayerId.of("PLAYER_5"));

        List<Player> players = playerJpaRepository.readPlayers(playerIds);

        Assert.assertEquals(3, players.size());
        Assert.assertTrue(players.stream().anyMatch(player -> player.getPlayerId().equals(PlayerId.of("PLAYER_1"))));
        Assert.assertTrue(players.stream().anyMatch(player -> player.getPlayerId().equals(PlayerId.of("PLAYER_2"))));
        Assert.assertTrue(players.stream().anyMatch(player -> player.getPlayerId().equals(PlayerId.of("PLAYER_5"))));
    }

    @Test
    @Transactional
    @Sql("classpath:save_player.sql")
    public void shouldSavePlayer() {
        PlayerId playerId = PlayerId.of("PLAYER_3");
        PlayerName playerName = PlayerName.of("PLAYER_NAME_3");
        Player player = Player.newPlayer(playerId, playerName);

        playerJpaRepository.save(player);

        PlayerJpaEntity playerJpaEntity = entityManager.find(PlayerJpaEntity.class, playerId.getId());
        Assert.assertNotNull(playerJpaEntity);
        Assert.assertEquals(playerName, playerJpaEntity.getPlayerName());
        Assert.assertEquals(playerId, playerJpaEntity.getPlayerId());
        Assert.assertEquals(Earnings.zero(), playerJpaEntity.getEarnings());
        Assert.assertEquals(NumberOfPlays.zero(), playerJpaEntity.getNumberOfPlays());
    }

    @Test
    @Transactional
    @Sql("classpath:save_player.sql")
    public void shouldUpdatePlayer() {
        PlayerId playerId = PlayerId.of("PLAYER_1");
        PlayerName playerName = PlayerName.of("PLAYER_NAME_1");
        Earnings liveEarnings = Earnings.of(50);
        NumberOfPlays numberOfPlays = NumberOfPlays.of(5);
        Player player = Player.of(playerId, playerName, liveEarnings, numberOfPlays);
        playerJpaRepository.save(player);
        PlayerJpaEntity playerJpaEntity = entityManager.find(PlayerJpaEntity.class, playerId.getId());

        Assert.assertNotNull(playerJpaEntity);
        Assert.assertEquals(playerName, playerJpaEntity.getPlayerName());
        Assert.assertEquals(playerId, playerJpaEntity.getPlayerId());
        Assert.assertEquals(Earnings.of(50), playerJpaEntity.getEarnings());
        Assert.assertEquals(NumberOfPlays.of(5), playerJpaEntity.getNumberOfPlays());
    }

    @Test
    @Transactional
    @Sql("classpath:player_existence.sql")
    public void shouldCheckIfPlayerExist() {
        PlayerName playerName = PlayerName.of("PLAYER_NAME_2");
        boolean playerExist = playerJpaRepository.playerExist(playerName);
        Assert.assertTrue(playerExist);
    }

    @Test
    @Transactional
    @Sql("classpath:player_existence.sql")
    public void shouldCheckIfPlayerNotExist() {
        PlayerName playerName = PlayerName.of("PLAYER_NAME_9");
        boolean playerExist = playerJpaRepository.playerExist(playerName);
        Assert.assertFalse(playerExist);
    }

}
