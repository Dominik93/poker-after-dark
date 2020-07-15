package com.slusarz.pokerafterdark.infrastructure.persistence.mapper;

import com.slusarz.pokerafterdark.domain.cashgame.CashGameParticipant;
import com.slusarz.pokerafterdark.domain.tournament.TournamentParticipant;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.participation.ParticipationJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParticipationEntityMapper {

    public ParticipationJpaEntity toParticipationJpaEntity(PlayerJpaEntity playerJpaEntity, CashGameParticipant cashGameParticipant) {
        return ParticipationJpaEntity.builder()
                .earnings(cashGameParticipant.getEarnings().getValue())
                .player(playerJpaEntity)
                .build();
    }

    public ParticipationJpaEntity toParticipationJpaEntity(PlayerJpaEntity playerJpaEntity, TournamentParticipant cashGameParticipant) {
        return ParticipationJpaEntity.builder()
                .earnings(cashGameParticipant.getEarnings().getValue())
                .player(playerJpaEntity)
                .build();
    }

}
