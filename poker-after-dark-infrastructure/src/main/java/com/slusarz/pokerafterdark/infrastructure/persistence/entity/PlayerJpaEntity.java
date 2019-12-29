package com.slusarz.pokerafterdark.infrastructure.persistence.entity;

import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter
@Entity
@Table(name = "PLAYER")
@NoArgsConstructor
public class PlayerJpaEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Setter
    @Column(name = "LIVE_WINNINGS")
    private double liveWinnings;

    @Setter
    @Column(name = "NUMBER_OF_PLAYS")
    private int numberOfPlays;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<ParticipationJpaEntity> participations = new ArrayList<>();

    @OneToMany(mappedBy = "host")
    private List<GameJpaEntity> games = new ArrayList<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<ProfitJpaEntity> profits = new ArrayList<>();

    @Builder
    public PlayerJpaEntity(String id,
                           String name,
                           double liveWinnings,
                           int numberOfPlays,
                           List<ParticipationJpaEntity> participations,
                           List<GameJpaEntity> games,
                           List<ProfitJpaEntity> profits) {
        this.id = id;
        this.name = name;
        this.liveWinnings = liveWinnings;
        this.numberOfPlays = numberOfPlays;
        this.participations = Optional.ofNullable(participations).orElse(new ArrayList<>());
        this.games = Optional.ofNullable(games).orElse(new ArrayList<>());
        this.profits = Optional.ofNullable(profits).orElse(new ArrayList<>());
    }

    public void addProfit(ProfitJpaEntity profitJpaEntity) {
        profitJpaEntity.setPlayer(this);
        this.profits.add(profitJpaEntity);
    }

    public PlayerId getPlayerId() {
        return PlayerId.of(id);
    }

    public PlayerName getPlayerName() {
        return PlayerName.of(name);
    }

    public Earnings getEarnings() {
        return Earnings.of(liveWinnings);
    }

}
