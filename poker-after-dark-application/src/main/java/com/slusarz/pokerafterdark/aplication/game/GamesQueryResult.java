package com.slusarz.pokerafterdark.aplication.game;

import com.slusarz.pokerafterdark.aplication.cqrs.query.QueryResult;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class GamesQueryResult implements QueryResult {

    private List<GameProjection> games;

}
