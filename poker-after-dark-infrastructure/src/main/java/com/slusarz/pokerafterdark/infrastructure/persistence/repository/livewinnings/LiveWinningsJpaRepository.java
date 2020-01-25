package com.slusarz.pokerafterdark.infrastructure.persistence.repository.livewinnings;

import com.slusarz.pokerafterdark.application.livewinnings.LiveWinningsRepository;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@NoArgsConstructor
public class LiveWinningsJpaRepository implements LiveWinningsRepository {

    private static final String UPDATE_LIVE_WINNINGS = "update player set live_winnings = (" +
            "select coalesce(sum(participation.earnings), 0) from participation where player_id = player.id)";

    private static final String UPDATE_NUMBERS_OF_PLAYS = "update player set number_of_plays = (" +
            "select coalesce(count(participation.earnings), 0) from participation where player_id = player.id)";

    @PersistenceContext
    private EntityManager entityManager;

    public void synchronize() {
        entityManager.createNativeQuery(UPDATE_LIVE_WINNINGS).executeUpdate();
        entityManager.createNativeQuery(UPDATE_NUMBERS_OF_PLAYS).executeUpdate();
    }

}
