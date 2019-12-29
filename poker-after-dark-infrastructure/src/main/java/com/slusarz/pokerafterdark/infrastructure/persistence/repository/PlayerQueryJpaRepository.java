package com.slusarz.pokerafterdark.infrastructure.persistence.repository;

import com.slusarz.pokerafterdark.aplication.player.PlayerProjection;
import com.slusarz.pokerafterdark.aplication.player.PlayerQueryRepository;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class PlayerQueryJpaRepository implements PlayerQueryRepository {

    private static final String SELECT_PLAYERS = "select p from PlayerJpaEntity p order by p.numberOfPlays desc";

    private static final String SELECT_EARNINGS = "select player_id, max(earnings), min(earnings) from participation group by player_id";

    @PersistenceContext
    private EntityManager entityManager;

    private PlayerEntityMapper playerEntityMapper;

    public PlayerQueryJpaRepository(PlayerEntityMapper playerEntityMapper) {
        this.playerEntityMapper = playerEntityMapper;
    }

    public List<PlayerProjection> readPlayers() {
        List<PlayerJpaEntity> playerJpaEntities = entityManager.createQuery(SELECT_PLAYERS).getResultList();
        List<Object[]> resultList = entityManager.createNativeQuery(SELECT_EARNINGS).getResultList();

        Map<PlayerId, Earnings> maxWin = resultList.stream().collect(Collectors.toMap(o -> PlayerId.of(o[0].toString()),
                o -> Earnings.of(Double.valueOf(o[1].toString()))));
        Map<PlayerId, Earnings> minWin = resultList.stream().collect(Collectors.toMap(o -> PlayerId.of(o[0].toString()),
                o -> Earnings.of(Double.valueOf(o[2].toString()))));

        return playerJpaEntities.stream().map(playerJpaEntity -> playerEntityMapper.toPlayerProjection(playerJpaEntity, maxWin, minWin)).collect(Collectors.toList());
    }

}
