package com.slusarz.pokerafterdark.infrastructure.persistence.mapper;

import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.ParticipationJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.PlayerJpaEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParticipationEntityMapper {

    public ParticipationJpaEntity toParticipationJpaEntity(PlayerJpaEntity playerJpaEntity, Participant participant) {
        return ParticipationJpaEntity.builder()
                .earnings(participant.getEarnings().getValue())
                .player(playerJpaEntity)
                .build();
    }


    public Participant toParticipation(ParticipationJpaEntity participationJpaEntity) {
        return Participant.of(PlayerId.of(participationJpaEntity.getPlayer().getId()), participationJpaEntity.getEarnings());
    }

}
