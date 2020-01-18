package com.slusarz.pokerafterdark.infrastructure.persistence.mapper;

import com.slusarz.pokerafterdark.application.game.GameProjection;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.Host;
import com.slusarz.pokerafterdark.domain.game.Pot;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.PlayerJpaEntity;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GameEntityMapper {

    private ParticipationEntityMapper participationEntityMapper;

    public GameJpaEntity toGameJpaEntity(PlayerJpaEntity playerJpaEntity, Game game) {
        return GameJpaEntity.builder()
                .id(game.getGameId().getId())
                .date(game.getDate())
                .pot(game.getPot().getValue())
                .host(playerJpaEntity)
                .build();
    }

    public GameProjection toGame(GameJpaEntity gameJpaEntity, Map<GameId, List<Participant>> participants, List<PlayerId> playerIds) {
        PlayerId playerId = Optional.ofNullable(gameJpaEntity.getHost()).map(PlayerJpaEntity::getId).map(PlayerId::of).orElse(PlayerId.empty());
        PlayerName playerName = Optional.ofNullable(gameJpaEntity.getHost()).map(PlayerJpaEntity::getName).map(PlayerName::of).orElse(PlayerName.unknown());
        Host player = Host.of(playerId, playerName);
        GameId gameId = gameJpaEntity.getGameId();
        List<Participant> participantList = participants.getOrDefault(gameId, Collections.emptyList());
        if (playerIds.isEmpty()) {
            return GameProjection.of(gameId, player, gameJpaEntity.getDate(), Pot.of(gameJpaEntity.getPot()), participantList);
        }
        List<Participant> filteredParticipants = participantList.stream()
                .filter(participant -> playerIds.contains(participant.getPlayerId()))
                .collect(Collectors.toList());
        return GameProjection.of(gameId, player, gameJpaEntity.getDate(), Pot.of(gameJpaEntity.getPot()), filteredParticipants);
    }

    public Game toGame(GameJpaEntity gameJpaEntity) {
        List<Participant> participants = gameJpaEntity.getParticipations().stream().map(participationEntityMapper::toParticipation).collect(Collectors.toList());
        PlayerId playerId = Optional.ofNullable(gameJpaEntity.getHost())
                .map(PlayerJpaEntity::getId).map(PlayerId::of)
                .orElse(PlayerId.empty());
        GameId gameId = gameJpaEntity.getGameId();
        return Game.of(gameId, playerId, gameJpaEntity.getDate(), Pot.of(gameJpaEntity.getPot()), participants);
    }
}
