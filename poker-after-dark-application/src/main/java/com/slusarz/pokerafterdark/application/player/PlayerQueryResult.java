package com.slusarz.pokerafterdark.application.player;

import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryResult;
import lombok.Value;

@Value(staticConstructor = "of")
public class PlayerQueryResult implements QueryResult {

    private PlayerProjection player;

}
