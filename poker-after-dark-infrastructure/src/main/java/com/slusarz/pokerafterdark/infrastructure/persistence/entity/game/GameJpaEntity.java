package com.slusarz.pokerafterdark.infrastructure.persistence.entity.game;

import com.slusarz.pokerafterdark.domain.game.GameType;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.participation.ParticipationJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.place.PlaceJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.profit.ProfitJpaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
    private LocalDate occurrenceDate;

    @Column(name = "POT")
    private Integer pot;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private GameType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_ID")
    private PlayerJpaEntity host;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<ParticipationJpaEntity> participations = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<ProfitJpaEntity> profits;

    @OrderBy("place")
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<PlaceJpaEntity> places = new ArrayList<>();

    @Builder
    public GameJpaEntity(String id,
                         LocalDate occurrenceDate,
                         Integer pot,
                         PlayerJpaEntity host,
                         GameType gameType,
                         List<ParticipationJpaEntity> participations,
                         List<PlaceJpaEntity> places,
                         List<ProfitJpaEntity> profits) {
        this.id = id;
        this.occurrenceDate = occurrenceDate;
        this.type = gameType;
        this.pot = pot;
        this.host = host;
        this.participations = Optional.ofNullable(participations).orElse(new ArrayList<>());
        this.places = Optional.ofNullable(places).orElse(new ArrayList<>());
        this.profits = Optional.ofNullable(profits).orElse(new ArrayList<>());
    }

    public void addParticipation(ParticipationJpaEntity participationJpaEntity) {
        participationJpaEntity.setGame(this);
        this.participations.add(participationJpaEntity);
    }

    public void addPlace(PlaceJpaEntity placeJpaEntity) {
        placeJpaEntity.setGame(this);
        this.places.add(placeJpaEntity);
    }

}
