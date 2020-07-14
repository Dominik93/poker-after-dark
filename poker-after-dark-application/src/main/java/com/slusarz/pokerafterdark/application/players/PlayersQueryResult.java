package com.slusarz.pokerafterdark.application.players;

import com.slusarz.pokerafterdark.application.cqrs.query.QueryResult;
import com.slusarz.pokerafterdark.application.player.PlayerProjection;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class PlayersQueryResult implements QueryResult {

    private List<PlayerProjection> players;

}
