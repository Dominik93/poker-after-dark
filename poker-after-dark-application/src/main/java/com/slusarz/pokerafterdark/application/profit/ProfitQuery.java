package com.slusarz.pokerafterdark.application.profit;

import com.slusarz.pokerafterdark.application.cqrs.query.Query;
import com.slusarz.pokerafterdark.domain.game.GameType;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value(staticConstructor = "of")
public class ProfitQuery implements Query {

    private LocalDate from;

    private LocalDate to;

    private List<GameType> gameType;

    private List<PlayerId> playerIds;

}
