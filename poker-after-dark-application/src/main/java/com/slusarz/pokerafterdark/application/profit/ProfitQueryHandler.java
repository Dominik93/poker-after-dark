package com.slusarz.pokerafterdark.application.profit;

import com.slusarz.pokerafterdark.application.config.ConfigProvider;
import com.slusarz.pokerafterdark.application.cqrs.query.QueryHandler;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProfitQueryHandler implements QueryHandler<ProfitQueryResult, ProfitQuery> {

    private ProfitQueryRepository gameJpaRepository;

    private ConfigProvider configProvider;

    @Override
    public ProfitQueryResult handle(ProfitQuery profitQuery) {
        LocalDate from = Optional.ofNullable(profitQuery.getFrom()).orElse(configProvider.getPagesFrom());
        LocalDate to = Optional.ofNullable(profitQuery.getTo()).orElse(configProvider.getPagesTo());
        List<Profit> profits = gameJpaRepository.getProfits(profitQuery.getGameType(), from, to, profitQuery.getPlayerIds());
        return ProfitQueryResult.of(profits);
    }
}
