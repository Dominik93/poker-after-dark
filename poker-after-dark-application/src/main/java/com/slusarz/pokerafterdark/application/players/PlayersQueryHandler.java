package com.slusarz.pokerafterdark.application.players;

import com.slusarz.pokerafterdark.application.cqrs.query.QueryHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayersQueryHandler implements QueryHandler<PlayersQueryResult, PlayersQuery> {

    private PlayersQueryRepository playersJpaRepository;

    @Override
    public PlayersQueryResult handle(PlayersQuery playersQuery) {
        return PlayersQueryResult.of(playersJpaRepository.readPlayers());
    }
}
