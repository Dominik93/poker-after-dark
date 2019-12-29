package com.slusarz.pokerafterdark.infrastructure.persistence.entity;

import com.slusarz.pokerafterdark.domain.game.GameId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Entity
@Table(name = "GAME")
@NoArgsConstructor
public class GameJpaEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "POT")
    private Integer pot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_ID")
    private PlayerJpaEntity host;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<ParticipationJpaEntity> participations = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<ProfitJpaEntity> profits;

    @Builder
    public GameJpaEntity(String id, LocalDate date, Integer pot, PlayerJpaEntity host, List<ParticipationJpaEntity> participations,
                         List<ProfitJpaEntity> profits) {
        this.id = id;
        this.date = date;
        this.pot = pot;
        this.host = host;
        this.participations = Optional.ofNullable(participations).orElse(new ArrayList<>());
        this.profits = Optional.ofNullable(profits).orElse(new ArrayList<>());
    }

    public void addParticipation(ParticipationJpaEntity participationJpaEntity) {
        participationJpaEntity.setGame(this);
        this.participations.add(participationJpaEntity);
    }

    public GameId getGameId(){
        return GameId.of(id);
    }

}
