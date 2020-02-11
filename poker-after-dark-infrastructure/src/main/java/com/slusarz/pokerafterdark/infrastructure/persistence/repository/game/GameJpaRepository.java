package com.slusarz.pokerafterdark.infrastructure.persistence.repository.game;

import com.slusarz.pokerafterdark.application.usecase.addgame.GameRepository;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameEntityMapper;
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
public class GameJpaRepository implements GameRepository {

    private static final String SELECT_PLAYERS = "select u from PlayerJpaEntity u ";

    @PersistenceContext
    private EntityManager entityManager;

    private GameEntityMapper gameEntityMapper;

    private ParticipationEntityMapper participationEntityMapper;

    public GameJpaRepository(GameEntityMapper gameEntityMapper, ParticipationEntityMapper participationEntityMapper) {
        this.gameEntityMapper = gameEntityMapper;
        this.participationEntityMapper = participationEntityMapper;
    }

    @Override
    public GameId generateId() {
        return GameId.of(UUID.randomUUID().toString());
    }

    @Override
    public Game save(Game game) {
        List<PlayerJpaEntity> playerJpaEntities = entityManager.createQuery(SELECT_PLAYERS).getResultList();
        Map<PlayerId, PlayerJpaEntity> players = playerJpaEntities.stream().collect(Collectors.toMap(PlayerJpaEntity::getPlayerId, Function.identity()));

        GameJpaEntity gameJpaEntity = gameEntityMapper.toGameJpaEntity(players.get(game.getHost()), game);

        game.getParticipants()
                .stream()
                .map(participant -> participationEntityMapper.toParticipationJpaEntity(players.get(participant.getPlayerId()), participant))
                .forEach(gameJpaEntity::addParticipation);
        entityManager.persist(gameJpaEntity);

        return gameEntityMapper.toGame(gameJpaEntity);
    }

    @Override
    public void remove(GameId gameId) {
        GameJpaEntity gameJpaEntity = entityManager.find(GameJpaEntity.class, gameId.getId());
        entityManager.remove(gameJpaEntity);
    }
}
