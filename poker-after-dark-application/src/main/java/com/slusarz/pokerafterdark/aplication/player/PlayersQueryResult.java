package com.slusarz.pokerafterdark.aplication.player;

import com.slusarz.pokerafterdark.aplication.cqrs.query.QueryResult;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class PlayersQueryResult implements QueryResult {

    private List<PlayerProjection> players;

}
