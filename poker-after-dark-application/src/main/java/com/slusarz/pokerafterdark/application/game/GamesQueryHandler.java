package com.slusarz.pokerafterdark.application.game;

import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GamesQueryHandler implements QueryHandler<GamesQueryResult, GamesQuery> {

    private GameQueryRepository gameJpaRepository;

    @Override
    public GamesQueryResult handle(GamesQuery gamesQuery) {
        return GamesQueryResult.of(gameJpaRepository.read(gamesQuery.getFrom(), gamesQuery.getTo(), gamesQuery.getPlayerIds()));
    }

}
