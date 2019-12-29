package com.slusarz.pokerafterdark.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Entity
@Table(name = "PROFIT")
@AllArgsConstructor
@NoArgsConstructor
public class ProfitJpaEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "LP")
    private int lp;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "PROFIT")
    private Double profit;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "GAME_ID")
    private GameJpaEntity game;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PLAYER_ID")
    private PlayerJpaEntity player;

    @Builder
    public ProfitJpaEntity(int lp, LocalDate date, Double profit, PlayerJpaEntity player, GameJpaEntity game) {
        this.id = UUID.randomUUID().toString();
        this.lp = lp;
        this.date = date;
        this.profit = profit;
        this.player = player;
        this.game = game;
    }
}
