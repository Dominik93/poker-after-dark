package com.slusarz.pokerafterdark.infrastructure.persistence.mapper;

import com.slusarz.pokerafterdark.application.player.PlayerProjection;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.PlayerJpaEntity;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class PlayerEntityMapper {

    public Player toPlayer(PlayerJpaEntity playerJpaEntity) {
        return Player.of(playerJpaEntity.getPlayerId(),
                playerJpaEntity.getPlayerName(),
                Earnings.of(playerJpaEntity.getLiveWinnings()),
                playerJpaEntity.getNumberOfPlays());
    }

    public PlayerProjection toPlayerProjection(PlayerJpaEntity playerJpaEntity,
                                               Map<PlayerId, Earnings> maxWin,
                                               Map<PlayerId, Earnings> minWin) {
        PlayerId playerId = playerJpaEntity.getPlayerId();
        return PlayerProjection.of(playerId,
                playerJpaEntity.getPlayerName(),
                playerJpaEntity.getEarnings(),
                maxWin.getOrDefault(playerId, Earnings.zero()),
                minWin.getOrDefault(playerId, Earnings.zero()),
                playerJpaEntity.getNumberOfPlays());
    }
}
