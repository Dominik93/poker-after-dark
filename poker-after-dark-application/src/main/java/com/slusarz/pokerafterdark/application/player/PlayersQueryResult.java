package com.slusarz.pokerafterdark.application.player;

import com.slusarz.pokerafterdark.application.cqrs.query.QueryResult;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class PlayersQueryResult implements QueryResult {

    private List<PlayerProjection> players;

}
