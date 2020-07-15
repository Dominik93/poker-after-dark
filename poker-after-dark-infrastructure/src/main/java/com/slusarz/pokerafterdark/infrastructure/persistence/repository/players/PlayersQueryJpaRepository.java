package com.slusarz.pokerafterdark.infrastructure.persistence.repository.players;

import com.slusarz.pokerafterdark.application.player.PlayerProjection;
import com.slusarz.pokerafterdark.application.players.PlayersQueryRepository;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class PlayersQueryJpaRepository implements PlayersQueryRepository {

    private static final String SELECT_PLAYERS
            = "select p from PlayerJpaEntity p order by p.numberOfPlays desc";

    private static final String SELECT_EARNINGS
            = "select player_id, max(earnings), min(earnings) from participation group by player_id";

    @PersistenceContext
    private EntityManager entityManager;

    private PlayerEntityMapper playerEntityMapper;

    public PlayersQueryJpaRepository(PlayerEntityMapper playerEntityMapper) {
        this.playerEntityMapper = playerEntityMapper;
    }

    public List<PlayerProjection> readPlayers() {
        List<PlayerJpaEntity> playerJpaEntities = entityManager.createQuery(SELECT_PLAYERS).getResultList();
        List<Object[]> resultList = entityManager.createNativeQuery(SELECT_EARNINGS).getResultList();

        Map<PlayerId, Earnings> maxWins
                = resultList.stream().collect(Collectors.toMap(this::toPlayerId, this::toMaxEarnings));
        Map<PlayerId, Earnings> minWins
                = resultList.stream().collect(Collectors.toMap(this::toPlayerId, this::toMinEarnings));

        return playerJpaEntities.stream()
                .map(playerJpaEntity -> playerEntityMapper.toPlayerProjection(playerJpaEntity, maxWins, minWins))
                .collect(Collectors.toList());
    }

    private PlayerId toPlayerId(Object[] result) {
        return PlayerId.of(result[0].toString());
    }

    private Earnings toMinEarnings(Object[] result) {
        return Earnings.of(Double.valueOf(result[2].toString()));
    }

    private Earnings toMaxEarnings(Object[] result) {
        return Earnings.of(Double.valueOf(result[1].toString()));
    }

}
