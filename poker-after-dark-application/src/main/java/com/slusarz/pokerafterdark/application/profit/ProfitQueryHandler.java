package com.slusarz.pokerafterdark.application.profit;

import com.slusarz.pokerafterdark.application.cqrs.query.QueryHandler;
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
