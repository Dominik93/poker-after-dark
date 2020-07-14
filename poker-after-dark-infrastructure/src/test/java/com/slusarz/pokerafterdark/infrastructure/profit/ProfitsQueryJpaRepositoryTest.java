package com.slusarz.pokerafterdark.infrastructure.profit;

import com.slusarz.pokerafterdark.domain.game.GameType;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import com.slusarz.pokerafterdark.infrastructure.TestJpaConfiguration;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ProfitEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.profit.ProfitQueryJpaRepository;
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
public class ProfitsQueryJpaRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private ProfitQueryJpaRepository profitQueryJpaRepository;

    @Before
    public void init() {
        profitQueryJpaRepository = new ProfitQueryJpaRepository(entityManager, new ProfitEntityMapper());
    }

    @Test
    @Transactional
    @Sql("classpath:read_players.sql")
    public void shouldReadProfits() {
        LocalDate from = LocalDate.of(2018, 9, 22);
        LocalDate to = LocalDate.of(2019, 8, 30);
        GameType gameType = GameType.CASH;
        List<Profit> profits = profitQueryJpaRepository.getProfits(Collections.singletonList(gameType), from, to, Collections.emptyList());

        Assert.assertFalse(profits.isEmpty());
        Assert.assertEquals(5, profits.size());
    }
}
