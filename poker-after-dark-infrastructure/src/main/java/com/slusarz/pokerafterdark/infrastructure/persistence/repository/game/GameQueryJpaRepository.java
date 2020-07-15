package com.slusarz.pokerafterdark.infrastructure.persistence.repository.game;

import com.slusarz.pokerafterdark.application.game.GameProjection;
import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.application.game.ParticipantProjection;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameProjectionEntityMapper;
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

    private GameCaller gameCaller;

    private GameProjectionEntityMapper gameProjectionEntityMapper;

    public GameQueryJpaRepository(GameQueryCaller gameQueryCaller,
                                  GameCaller gameCaller,
                                  GameProjectionEntityMapper gameProjectionEntityMapper) {
        this.gameQueryCaller = gameQueryCaller;
        this.gameCaller = gameCaller;
        this.gameProjectionEntityMapper = gameProjectionEntityMapper;
    }

    public List<GameProjection> read(LocalDate from, LocalDate to, List<PlayerId> playerIds) {
        List<GameJpaEntity> gameJpaEntities = gameQueryCaller.selectGames(from, to);

        List<String> gameIds = gameJpaEntities.stream().map(GameJpaEntity::getId).collect(Collectors.toList());

        List<ParticipationResult> participationJpaEntities = gameQueryCaller.selectParticipations(gameIds);


        Map<GameId, List<ParticipantProjection>> participants = participationJpaEntities.stream()
                .collect(Collectors.groupingBy(ParticipationResult::getGameId))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, object -> toListOfParticipants(object.getValue())));

        GameJpaEntity lastGame = gameCaller.readLast();

        return gameJpaEntities.stream()
                .map(gameJpaEntity -> gameProjectionEntityMapper.toGameProjection(lastGame, gameJpaEntity, participants, playerIds))
                .collect(Collectors.toList());
    }

    private List<ParticipantProjection> toListOfParticipants(List<ParticipationResult> results) {
        return results.stream()
                .map(result -> ParticipantProjection.of(result.getPlayerId(), result.getPlayerName(), result.getEarnings(), result.getPlace()))
                .collect(Collectors.toList());
    }

}
