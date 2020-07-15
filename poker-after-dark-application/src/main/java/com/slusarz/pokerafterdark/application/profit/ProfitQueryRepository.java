package com.slusarz.pokerafterdark.application.profit;

import com.slusarz.pokerafterdark.domain.game.GameType;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.profit.Profit;

import java.time.LocalDate;
import java.util.List;

public interface ProfitQueryRepository {
    List<Profit> getProfits(List<GameType> gameType, LocalDate from, LocalDate to, List<PlayerId> playerIds);
}
