package com.slusarz.pokerafterdark.application.game;

import com.slusarz.pokerafterdark.application.common.cqrs.query.Query;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
@Value(staticConstructor = "of")
public class GamesQuery implements Query {

    private LocalDate from;

    private LocalDate to;

    private List<PlayerId> playerIds;

}
