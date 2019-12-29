package com.slusarz.pokerafterdark.aplication.profit;

import com.slusarz.pokerafterdark.aplication.cqrs.query.QueryHandler;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProfitQueryHandler implements QueryHandler<ProfitQueryResult, ProfitQuery> {

    private ProfitQueryRepository gameJpaRepository;

    @Override
    public ProfitQueryResult handle(ProfitQuery profitQuery) {
        List<Profit> profits = gameJpaRepository.getProfits(profitQuery.getFrom(), profitQuery.getTo(), profitQuery.getPlayerIds());
        return ProfitQueryResult.of(profits);
    }
}
