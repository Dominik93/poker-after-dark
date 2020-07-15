package com.slusarz.pokerafterdark.infrastructure.persistence.mapper;

import com.slusarz.pokerafterdark.domain.cashgame.CashGame;
import com.slusarz.pokerafterdark.domain.game.GameType;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CashGameEntityMapper {

    public GameJpaEntity toGameJpaEntity(PlayerJpaEntity playerJpaEntity, CashGame cashGame) {
        return GameJpaEntity.builder()
                .id(cashGame.getCashGameId().getId())
                .occurrenceDate(cashGame.getOccurrenceDate())
                .pot(cashGame.getPot().getValue())
                .host(playerJpaEntity)
                .gameType(GameType.CASH)
                .build();
    }

}
