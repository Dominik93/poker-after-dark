package com.slusarz.pokerafterdark.application.players;

import com.slusarz.pokerafterdark.application.cqrs.query.Query;
import lombok.Value;

@Value(staticConstructor = "of")
public class PlayersQuery implements Query {
}
