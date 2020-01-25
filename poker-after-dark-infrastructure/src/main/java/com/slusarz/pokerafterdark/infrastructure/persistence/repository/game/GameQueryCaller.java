package com.slusarz.pokerafterdark.infrastructure.persistence.repository.game;

import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.result.ParticipationResult;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class GameQueryCaller {

    private static final String SELECT_GAMES_BETWEEN_DATE = "select g from GameJpaEntity g where g.date BETWEEN :startDate AND :endDate order by g.date desc";
    private static final String START_DATE_PARAM = "startDate";
    private static final String END_DATE_PARAM = "endDate";

    private static final String SELECT_PARTICIPATION = "select game_id, player_id, earnings from PARTICIPATION where game_id in (:gameIds)";
    private static final String GAMES_IDS_PARAM = "gameIds";

    private static final String SELECT_LAST_GAME = "select g from GameJpaEntity g order by g.date desc limit 1";


    @PersistenceContext
    private EntityManager entityManager;

    GameJpaEntity selectLastGame() {
        return (GameJpaEntity) entityManager.createQuery(SELECT_LAST_GAME)
                .getSingleResult();
    }

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
                Earnings.of(Double.valueOf(objects[2].toString())));
    }

}
