package com.slusarz.pokerafterdark.infrastructure.profit;

import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.Pot;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import com.slusarz.pokerafterdark.infrastructure.TestJpaConfiguration;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.ProfitJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ProfitEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.profit.ProfitJpaRepository;
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
import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestJpaConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
public class ProfitRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private ProfitJpaRepository profitJpaRepository;

    @Before
    public void init() {
        profitJpaRepository = new ProfitJpaRepository(entityManager, new ProfitEntityMapper());
    }

    @Test
    @Transactional
    @Sql("classpath:read_profits.sql")
    public void shouldGetGameNumber() {
        int gameNumber = profitJpaRepository.getGameNumber();

        Assert.assertEquals(5, gameNumber);
    }


    @Test
    @Transactional
    @Sql("classpath:read_profits.sql")
    public void shouldGetProfit() {
        Profit profit = profitJpaRepository.getProfit(PlayerId.of("PLAYER_1"));

        Assert.assertEquals(5, profit.getWinnings().size());
    }

    @Test
    @Transactional
    @Sql("classpath:read_profits.sql")
    public void shouldAddProfit() {
        List<Participant> participants = new ArrayList<>();
        participants.add(Participant.of(PlayerId.of("PLAYER_2"), Earnings.of(-10)));
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(10)));
        GameId gameId = GameId.of("GAME_6");
        PlayerId host = PlayerId.of("PLAYER_1");
        Game game = Game.of(gameId, host, LocalDate.now(), Pot.of(20), participants);
        Profit profit = Profit.of(PlayerId.of("PLAYER_1"), PlayerName.of("PLAYER_NAME_1"), Collections.emptyList());

        profitJpaRepository.add(game, profit, Earnings.of(50), 6);

        List<ProfitJpaEntity> profitJpaEntities
                = entityManager.createQuery("SELECT p FROM ProfitJpaEntity p").getResultList();

        Assert.assertEquals(17, profitJpaEntities.size());
    }
}
