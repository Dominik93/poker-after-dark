package com.slusarz.pokerafterdark.infrastructure.persistence.mapper;

import com.slusarz.pokerafterdark.application.game.GameActions;
import com.slusarz.pokerafterdark.application.game.GameProjection;
import com.slusarz.pokerafterdark.application.game.ParticipantProjection;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.player.Host;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GameProjectionEntityMapper {

    public GameProjection toGameProjection(GameJpaEntity lastGame, GameJpaEntity gameJpaEntity,
                                           Map<GameId, List<ParticipantProjection>> participants,
                                           List<PlayerId> playerIds) {
        PlayerId playerId = Optional.ofNullable(gameJpaEntity.getHost()).map(PlayerJpaEntity::getId).map(PlayerId::of).orElse(PlayerId.empty());
        PlayerName playerName = Optional.ofNullable(gameJpaEntity.getHost()).map(PlayerJpaEntity::getName).map(PlayerName::of).orElse(PlayerName.unknown());

        Host player = Host.of(playerId, playerName);
        GameId gameId = GameId.of(gameJpaEntity.getId());
        List<ParticipantProjection> cashGameParticipantList = participants.getOrDefault(gameId, Collections.emptyList());
        Pot pot = Pot.of(gameJpaEntity.getPot());

        GameActions gameActions = GameActions.of(lastGame.getId().equals(gameJpaEntity.getId()));

        if (playerIds.isEmpty()) {
            return GameProjection.of(gameId,
                    gameActions,
                    gameJpaEntity.getType(),
                    player,
                    gameJpaEntity.getOccurrenceDate(),
                    pot,
                    cashGameParticipantList);
        }
        List<ParticipantProjection> filteredCashGameParticipants = cashGameParticipantList.stream()
                .filter(participant -> playerIds.contains(participant.getPlayerId()))
                .collect(Collectors.toList());
        return GameProjection.of(gameId,
                gameActions,
                gameJpaEntity.getType(),
                player,
                gameJpaEntity.getOccurrenceDate(),
                pot,
                filteredCashGameParticipants);
    }

}
