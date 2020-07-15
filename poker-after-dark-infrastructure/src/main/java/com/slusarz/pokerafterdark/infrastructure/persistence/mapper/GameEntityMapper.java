package com.slusarz.pokerafterdark.infrastructure.persistence.mapper;

import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class GameEntityMapper {

    public Game toGame(GameJpaEntity gameJpaEntity) {
        PlayerId playerId = getPlayerId(gameJpaEntity);
        GameId cashGameId = GameId.of(gameJpaEntity.getId());
        Pot pot = Pot.of(gameJpaEntity.getPot());
        return Game.of(cashGameId, playerId, gameJpaEntity.getOccurrenceDate(), pot);
    }

    private PlayerId getPlayerId(GameJpaEntity gameJpaEntity) {
        return Optional.ofNullable(gameJpaEntity.getHost()).map(PlayerJpaEntity::getPlayerId).orElse(PlayerId.empty());
    }
}
