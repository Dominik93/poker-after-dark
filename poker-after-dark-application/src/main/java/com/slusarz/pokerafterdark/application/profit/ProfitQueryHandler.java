package com.slusarz.pokerafterdark.application.profit;

import com.slusarz.pokerafterdark.application.config.ConfigProvider;
import com.slusarz.pokerafterdark.application.cqrs.query.QueryHandler;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class ProfitQueryHandler implements QueryHandler<ProfitQueryResult, ProfitQuery> {

    private ProfitQueryRepository gameJpaRepository;

    private ConfigProvider configProvider;

    @Override
    public ProfitQueryResult handle(ProfitQuery profitQuery) {
        if (profitQuery.getGameType().isEmpty()) {
            return ProfitQueryResult.of(Collections.emptyList());
        }

        LocalDate from = profitQuery.getFrom().orElse(configProvider.getPagesFrom());
        LocalDate to = profitQuery.getTo().orElse(configProvider.getPagesTo());
        List<Profit> profits = gameJpaRepository.getProfits(profitQuery.getGameType(), from, to, profitQuery.getPlayerIds());
        return ProfitQueryResult.of(profits);
    }
}
