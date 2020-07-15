package com.slusarz.pokerafterdark.infrastructure.persistence.repository.game;

import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.tournament.Place;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.result.ParticipationResult;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class GameQueryCaller {

    private static final String SELECT_GAMES_BETWEEN_DATE = "select g from GameJpaEntity g" +
            " where g.occurrenceDate BETWEEN :startDate AND :endDate" +
            " order by g.occurrenceDate desc";
    private static final String START_DATE_PARAM = "startDate";
    private static final String END_DATE_PARAM = "endDate";

    private static final String SELECT_PARTICIPATION = "select PARTICIPATION.game_id," +
            " PARTICIPATION.player_id," +
            " PLAYER.name," +
            " PARTICIPATION.earnings," +
            " PLACE.place" +
            " from PARTICIPATION" +
            " join PLAYER on PLAYER.ID = PARTICIPATION.player_id" +
            " LEFT JOIN PLACE on PLACE.GAME_ID = PARTICIPATION.GAME_ID AND PLACE.PLAYER_ID = PARTICIPATION.player_id" +
            " where PARTICIPATION.game_id in (:gameIds)";
    private static final String GAMES_IDS_PARAM = "gameIds";

    @PersistenceContext
    private EntityManager entityManager;

    List<GameJpaEntity> selectGames(LocalDate from, LocalDate to) {
        return (List<GameJpaEntity>) entityManager.createQuery(SELECT_GAMES_BETWEEN_DATE)
                .setParameter(START_DATE_PARAM, from)
                .setParameter(END_DATE_PARAM, to).getResultList();
    }

    List<ParticipationResult> selectParticipations(List<String> gameIds) {
        List<Object[]> resultList = entityManager.createNativeQuery(SELECT_PARTICIPATION)
                .setParameter(GAMES_IDS_PARAM, gameIds).getResultList();

        return resultList.stream().map(this::toResult).collect(Collectors.toList());
    }

    private ParticipationResult toResult(Object[] objects) {
        return ParticipationResult.of(GameId.of(objects[0].toString()),
                PlayerId.of(objects[1].toString()),
                PlayerName.of(objects[2].toString()),
                Earnings.of(Double.valueOf(objects[3].toString())),
                Optional.ofNullable(objects[4])
                        .map(Object::toString)
                        .map(Integer::valueOf)
                        .map(Place::of)
                        .orElse(null));
    }

}
