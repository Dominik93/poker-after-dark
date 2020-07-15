package com.slusarz.pokerafterdark.infrastructure.persistence.repository.cashgame;

import com.slusarz.pokerafterdark.domain.cashgame.CashGame;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameId;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameRepository;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.participation.ParticipationJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.CashGameEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ParticipationEntityMapper;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CashGameJpaRepository implements CashGameRepository {

    private static final String SELECT_PLAYERS = "select u from PlayerJpaEntity u ";

    @PersistenceContext
    private EntityManager entityManager;

    private CashGameEntityMapper cashGameEntityMapper;

    private ParticipationEntityMapper participationEntityMapper;

    public CashGameJpaRepository(CashGameEntityMapper cashGameEntityMapper, ParticipationEntityMapper participationEntityMapper) {
        this.cashGameEntityMapper = cashGameEntityMapper;
        this.participationEntityMapper = participationEntityMapper;
    }

    @Override
    public CashGameId generateId() {
        return CashGameId.of(UUID.randomUUID().toString());
    }

    @Override
    public void save(CashGame cashGame) {
        List<PlayerJpaEntity> playerJpaEntities = entityManager.createQuery(SELECT_PLAYERS).getResultList();
        Map<PlayerId, PlayerJpaEntity> players = playerJpaEntities.stream().collect(Collectors.toMap(PlayerJpaEntity::getPlayerId, Function.identity()));
        List<ParticipationJpaEntity> participationJpaEntities = toParticipationJpaEntity(cashGame, players);

        GameJpaEntity gameJpaEntity = cashGameEntityMapper.toGameJpaEntity(players.get(cashGame.getHost()), cashGame);
        participationJpaEntities.forEach(gameJpaEntity::addParticipation);
        entityManager.persist(gameJpaEntity);
    }

    private List<ParticipationJpaEntity> toParticipationJpaEntity(CashGame cashGame, Map<PlayerId, PlayerJpaEntity> players) {
        return cashGame.getCashGameParticipants()
                .stream()
                .map(participant -> participationEntityMapper.toParticipationJpaEntity(players.get(participant.getPlayerId()), participant))
                .collect(Collectors.toList());
    }

}
