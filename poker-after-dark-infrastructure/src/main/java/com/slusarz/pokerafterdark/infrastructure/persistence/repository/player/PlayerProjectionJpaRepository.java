package com.slusarz.pokerafterdark.infrastructure.persistence.repository.player;

import com.slusarz.pokerafterdark.application.livewinnings.PlayerProjectionRepository;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@NoArgsConstructor
public class PlayerProjectionJpaRepository implements PlayerProjectionRepository {

    private static final String UPDATE_LIVE_WINNINGS
            = "update player set live_winnings = " +
            "(select coalesce(sum(participation.earnings), 0) from participation where player_id = player.id)";

    private static final String UPDATE_NUMBERS_OF_PLAYS
            = "update player set number_of_plays = " +
            "(select coalesce(count(participation.earnings), 0) from participation where player_id = player.id)";

    private static final String UPDATE_MAX_EARNINGS
            = "update player set max_earnings = " +
            "(select coalesce(max(participation.earnings), 0) from participation where player_id = player.id)";

    private static final String UPDATE_MIN_EARNINGS
            = "update player set min_earnings = " +
            "(select coalesce(min(participation.earnings), 0) from participation where player_id = player.id)";

    @PersistenceContext
    private EntityManager entityManager;

    public void synchronize() {
        entityManager.createNativeQuery(UPDATE_LIVE_WINNINGS).executeUpdate();
        entityManager.createNativeQuery(UPDATE_NUMBERS_OF_PLAYS).executeUpdate();
        entityManager.createNativeQuery(UPDATE_MAX_EARNINGS).executeUpdate();
        entityManager.createNativeQuery(UPDATE_MIN_EARNINGS).executeUpdate();
    }

}
