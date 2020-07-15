package com.slusarz.pokerafterdark.infrastructure.cashgame;

import com.slusarz.pokerafterdark.domain.cashgame.CashGame;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameId;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameParticipant;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.infrastructure.TestJpaConfiguration;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.CashGameEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ParticipationEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.cashgame.CashGameJpaRepository;
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
public class CashGameJpaRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private CashGameJpaRepository cashGameJpaRepository;

    @Before
    public void init() {
        ParticipationEntityMapper participationEntityMapper = new ParticipationEntityMapper();
        CashGameEntityMapper gameEntityMapper = new CashGameEntityMapper();
        cashGameJpaRepository = new CashGameJpaRepository(entityManager, gameEntityMapper, participationEntityMapper);
    }

    @Test
    @Transactional
    @Sql("classpath:save_game.sql")
    public void shouldSaveGame() {
        List<CashGameParticipant> cashGameParticipants = new ArrayList<>();
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_2"), Earnings.of(-10)));
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(10)));
        CashGameId cashGameId = CashGameId.of("GAME_6");
        PlayerId host = PlayerId.of("PLAYER_1");
        CashGame cashGame = CashGame.of(cashGameId, host, LocalDate.now(), Pot.of(20), cashGameParticipants);

        cashGameJpaRepository.save(cashGame);

        GameJpaEntity gameJpaEntity = entityManager.find(GameJpaEntity.class, cashGameId.getId());
        Assert.assertNotNull(gameJpaEntity);
        Assert.assertEquals(2, gameJpaEntity.getParticipations().size());
    }

}
