package com.slusarz.pokerafterdark.application.game;

import com.slusarz.pokerafterdark.application.cqrs.query.QueryResult;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class GamesQueryResult implements QueryResult {

    private List<GameProjection> games;

}
