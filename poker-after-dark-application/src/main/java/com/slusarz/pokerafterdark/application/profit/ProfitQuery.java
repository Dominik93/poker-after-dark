package com.slusarz.pokerafterdark.application.profit;

import com.slusarz.pokerafterdark.application.cqrs.query.Query;
import com.slusarz.pokerafterdark.domain.game.GameType;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Value(staticConstructor = "of")
public class ProfitQuery implements Query {

    private LocalDate from;

    private LocalDate to;

    private List<GameType> gameType;

    private List<PlayerId> playerIds;

    public Optional<LocalDate> getFrom() {
        return Optional.ofNullable(from);
    }

    public Optional<LocalDate> getTo() {
        return Optional.ofNullable(to);
    }
}
