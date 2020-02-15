package com.slusarz.pokerafterdark.application.player;

import com.slusarz.pokerafterdark.application.cqrs.query.QueryHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerQueryHandler implements QueryHandler<PlayerQueryResult, PlayerQuery> {

    private PlayerQueryRepository playerJpaRepository;

    @Override
    public PlayerQueryResult handle(PlayerQuery playersQuery) {
        return PlayerQueryResult.of(playerJpaRepository.read(playersQuery.getPlayerId()));
    }
}
