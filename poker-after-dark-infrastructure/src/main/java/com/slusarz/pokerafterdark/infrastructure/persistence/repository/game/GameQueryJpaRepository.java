package com.slusarz.pokerafterdark.infrastructure.persistence.repository.game;

import com.slusarz.pokerafterdark.application.game.GameProjection;
import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.result.ParticipationResult;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GameQueryJpaRepository implements GameQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private GameQueryCaller gameQueryCaller;

    private GameEntityMapper gameEntityMapper;

    public GameQueryJpaRepository(GameQueryCaller gameQueryCaller, GameEntityMapper gameEntityMapper) {
        this.gameQueryCaller = gameQueryCaller;
        this.gameEntityMapper = gameEntityMapper;
    }

    public List<GameProjection> read(LocalDate from, LocalDate to, List<PlayerId> playerIds) {
        List<GameJpaEntity> gameJpaEntities = gameQueryCaller.selectGames(from, to);

        List<String> gameIds = gameJpaEntities.stream().map(GameJpaEntity::getId).collect(Collectors.toList());

        List<ParticipationResult> participationJpaEntities = gameQueryCaller.selectParticipations(gameIds);

        Map<GameId, List<Participant>> participants
                = participationJpaEntities.stream().collect(Collectors.groupingBy(ParticipationResult::getGameId))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, object -> toListOfParticipants(object.getValue())));

        return gameJpaEntities.stream()
                .map(gameJpaEntity -> gameEntityMapper.toGame(gameJpaEntity, participants, playerIds))
                .collect(Collectors.toList());
    }

    @Override
    public Game readLast() {
        return gameEntityMapper.toGame(gameQueryCaller.selectLastGame());
    }

    private List<Participant> toListOfParticipants(List<ParticipationResult> results) {
        return results.stream()
                .map(result -> Participant.of(result.getPlayerId(), result.getEarnings()))
                .collect(Collectors.toList());
    }

}
