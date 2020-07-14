package com.slusarz.pokerafterdark.infrastructure.persistence.mapper;

import com.slusarz.pokerafterdark.domain.game.GameType;
import com.slusarz.pokerafterdark.domain.tournament.Tournament;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TournamentEntityMapper {

    public GameJpaEntity toGameJpaEntity(PlayerJpaEntity playerJpaEntity, Tournament tournament) {
        return GameJpaEntity.builder()
                .id(tournament.getTournamentId().getId())
                .occurrenceDate(tournament.getOccurrenceDate())
                .pot(tournament.getPot().getValue())
                .host(playerJpaEntity)
                .gameType(GameType.TOURNAMENT)
                .build();
    }

}
