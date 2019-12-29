package com.slusarz.pokerafterdark.aplication.profit;

import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.profit.Profit;

import java.time.LocalDate;
import java.util.List;

public interface ProfitQueryRepository {
    List<Profit> getProfits(LocalDate from, LocalDate to, List<PlayerId> playerIds);
}
