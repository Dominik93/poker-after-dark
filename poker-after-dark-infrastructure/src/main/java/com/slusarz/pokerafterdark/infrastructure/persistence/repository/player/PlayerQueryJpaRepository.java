package com.slusarz.pokerafterdark.infrastructure.persistence.repository.player;

import com.slusarz.pokerafterdark.application.player.PlayerProjection;
import com.slusarz.pokerafterdark.application.player.PlayerQueryRepository;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@AllArgsConstructor
public class PlayerQueryJpaRepository implements PlayerQueryRepository {

    private static final String PLAYER_ID_PARAM = "playerId";

    private static final String SELECT_PLAYERS
            = "select p from PlayerJpaEntity p order by p.numberOfPlays desc";

    private static final String SELECT_EARNINGS
            = "select player_id, max(earnings), min(earnings) from participation group by player_id";

    private static final String SELECT_EARNINGS_FOR_PLAYER
            = "select player_id, max(earnings), min(earnings) from participation where player_id = :playerId group by player_id";

    @PersistenceContext
    private EntityManager entityManager;

    private PlayerEntityMapper playerEntityMapper;

    public PlayerQueryJpaRepository(PlayerEntityMapper playerEntityMapper) {
        this.playerEntityMapper = playerEntityMapper;
    }

    @Override
    public PlayerProjection read(PlayerId playerId) {
        PlayerJpaEntity playerJpaEntity = entityManager.find(PlayerJpaEntity.class, playerId.getId());
        Object[] result = (Object[]) entityManager.createNativeQuery(SELECT_EARNINGS_FOR_PLAYER)
                .setParameter(PLAYER_ID_PARAM, playerId.getId()).getSingleResult();
        return playerEntityMapper.toPlayerProjection(playerJpaEntity,
                toMaxEarnings(result),
                toMinEarnings(result));
    }

    private Earnings toMinEarnings(Object[] result) {
        return Earnings.of(Double.valueOf(result[2].toString()));
    }

    private Earnings toMaxEarnings(Object[] result) {
        return Earnings.of(Double.valueOf(result[1].toString()));
    }

}
