package com.slusarz.pokerafterdark.infrastructure.persistence.entity.participation;

import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Entity
@Table(name = "PARTICIPATION")
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationJpaEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private GameJpaEntity game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_ID")
    private PlayerJpaEntity player;

    @Column(name = "EARNINGS")
    private double earnings;

    @Builder
    public ParticipationJpaEntity(GameJpaEntity game, PlayerJpaEntity player, double earnings) {
        this.id = UUID.randomUUID().toString();
        this.game = game;
        this.player = player;
        this.earnings = earnings;
    }

    public Earnings getEarnings() {
        return Earnings.of(earnings);
    }

}
