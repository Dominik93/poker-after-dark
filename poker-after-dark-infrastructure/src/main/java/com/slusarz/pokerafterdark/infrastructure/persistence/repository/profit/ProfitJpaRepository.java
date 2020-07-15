package com.slusarz.pokerafterdark.infrastructure.persistence.repository.profit;

import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import com.slusarz.pokerafterdark.domain.profit.ProfitRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ProfitEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@AllArgsConstructor
public class ProfitJpaRepository implements ProfitRepository {

    private static final String SELECT_MAX_LP = "select max(p.lp) from ProfitJpaEntity p";

    @PersistenceContext
    private EntityManager entityManager;

    private ProfitEntityMapper profitEntityMapper;

    public ProfitJpaRepository(ProfitEntityMapper profitEntityMapper) {
        this.profitEntityMapper = profitEntityMapper;
    }

    @Override
    public Profit getProfit(PlayerId playerId) {
        PlayerJpaEntity playerJpaEntity = entityManager.find(PlayerJpaEntity.class, playerId.getId());
        return profitEntityMapper.toProfit(playerJpaEntity);
    }

    @Override
    public void add(Game cashGame, Profit profit, Earnings win, int gameNumber) {
        PlayerJpaEntity playerJpaEntity = entityManager.find(PlayerJpaEntity.class, profit.getPlayerId().getId());
        GameJpaEntity gameJpaEntity = entityManager.find(GameJpaEntity.class, cashGame.getGameId().getId());
        playerJpaEntity.addProfit(profitEntityMapper.toProfitEntity(gameJpaEntity, win.getValue(), gameNumber));
        entityManager.persist(playerJpaEntity);
        entityManager.flush();
    }

    @Override
    public int getGameNumber() {
        return (int) entityManager.createQuery(SELECT_MAX_LP).getSingleResult();
    }

}
