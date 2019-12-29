package com.slusarz.pokerafterdark.aplication.player;

import com.slusarz.pokerafterdark.aplication.cqrs.query.Query;
import lombok.Value;

@Value(staticConstructor = "of")
public class PlayersQuery implements Query {
}
