package com.slusarz.pokerafterdark.aplication.player;

import com.slusarz.pokerafterdark.aplication.cqrs.query.QueryHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayersQueryHandler implements QueryHandler<PlayersQueryResult, PlayersQuery> {

    private PlayerQueryRepository playerJpaRepository;

    @Override
    public PlayersQueryResult handle(PlayersQuery playersQuery) {
        return PlayersQueryResult.of(playerJpaRepository.readPlayers());
    }
}
