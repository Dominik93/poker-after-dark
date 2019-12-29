package com.slusarz.pokerafterdark.infrastructure.persistence.mapper;

import com.slusarz.pokerafterdark.aplication.player.PlayerProjection;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.NumberOfPlays;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.PlayerJpaEntity;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class PlayerEntityMapper {

    public Player toPlayer(PlayerJpaEntity playerJpaEntity) {
        return Player.of(PlayerId.of(playerJpaEntity.getId()),
                PlayerName.of(playerJpaEntity.getName()),
                Earnings.of(playerJpaEntity.getLiveWinnings()),
                NumberOfPlays.of(playerJpaEntity.getNumberOfPlays()));
    }

    public PlayerProjection toPlayerProjection(PlayerJpaEntity playerJpaEntity,
                                               Map<PlayerId, Earnings> maxWin,
                                               Map<PlayerId, Earnings> minWin) {
        PlayerId playerId = PlayerId.of(playerJpaEntity.getId());
        return PlayerProjection.of(playerId,
                PlayerName.of(playerJpaEntity.getName()),
                Earnings.of(playerJpaEntity.getLiveWinnings()),
                maxWin.getOrDefault(playerId, Earnings.of(0)),
                minWin.getOrDefault(playerId, Earnings.of(0)),
                NumberOfPlays.of(playerJpaEntity.getNumberOfPlays()));
    }
}
