package com.slusarz.pokerafterdark.infrastructure.persistence.repository.game;

import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@NoArgsConstructor
@AllArgsConstructor
public class GameCaller {

    private static final String SELECT_LAST_GAME = "select g from GameJpaEntity g order by g.occurrenceDate desc";

    @PersistenceContext
    private EntityManager entityManager;

    public GameJpaEntity readLast() {
        return (GameJpaEntity) entityManager.createQuery(SELECT_LAST_GAME)
                .setMaxResults(1)
                .getSingleResult();
    }

}
