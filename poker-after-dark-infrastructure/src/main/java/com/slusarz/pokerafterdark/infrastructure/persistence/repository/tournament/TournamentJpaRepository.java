package com.slusarz.pokerafterdark.infrastructure.persistence.repository.tournament;

import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.tournament.Tournament;
import com.slusarz.pokerafterdark.domain.tournament.TournamentId;
import com.slusarz.pokerafterdark.domain.tournament.TournamentRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.participation.ParticipationJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.place.PlaceJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ParticipationEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.TournamentEntityMapper;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TournamentJpaRepository implements TournamentRepository {

    private static final String SELECT_PLAYERS = "select u from PlayerJpaEntity u";

    @PersistenceContext
    private EntityManager entityManager;

    private TournamentEntityMapper tournamentEntityMapper;

    private ParticipationEntityMapper participationEntityMapper;

    public TournamentJpaRepository(TournamentEntityMapper tournamentEntityMapper,
                                   ParticipationEntityMapper participationEntityMapper) {
        this.tournamentEntityMapper = tournamentEntityMapper;
        this.participationEntityMapper = participationEntityMapper;
    }

    @Override
    public TournamentId generateId() {
        return TournamentId.of(UUID.randomUUID().toString());
    }

    @Override
    public void save(Tournament tournament) {
        List<PlayerJpaEntity> playerJpaEntities = entityManager.createQuery(SELECT_PLAYERS).getResultList();
        Map<PlayerId, PlayerJpaEntity> players = playerJpaEntities.stream().collect(Collectors.toMap(PlayerJpaEntity::getPlayerId, Function.identity()));
        List<ParticipationJpaEntity> participationJpaEntities = toParticipationJpaEntity(tournament, players);
        List<PlaceJpaEntity> placeJpaEntities = toPlaceJpaEntities(tournament, players);

        GameJpaEntity gameJpaEntity = tournamentEntityMapper.toGameJpaEntity(players.get(tournament.getHost()), tournament);
        participationJpaEntities.forEach(gameJpaEntity::addParticipation);
        placeJpaEntities.forEach(gameJpaEntity::addPlace);
        entityManager.persist(gameJpaEntity);
    }

    private List<PlaceJpaEntity> toPlaceJpaEntities(Tournament tournament, Map<PlayerId, PlayerJpaEntity> players) {
        return tournament.getParticipants().stream()
                .map(participant -> PlaceJpaEntity.builder()
                        .place(participant.getPlace().getPlace())
                        .player(players.get(participant.getPlayerId()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<ParticipationJpaEntity> toParticipationJpaEntity(Tournament cashGame, Map<PlayerId, PlayerJpaEntity> players) {
        return cashGame.getParticipants()
                .stream()
                .map(participant -> participationEntityMapper.toParticipationJpaEntity(
                        players.get(participant.getPlayerId()), participant))
                .collect(Collectors.toList());
    }
}
