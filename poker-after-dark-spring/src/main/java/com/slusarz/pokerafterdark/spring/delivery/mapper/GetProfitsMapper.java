package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.profit.ProfitQuery;
import com.slusarz.pokerafterdark.application.profit.ProfitQueryResult;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.game.GameType;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.specification.api.GetProfitRequest;
import com.slusarz.pokerafterdark.specification.api.GetProfitResponse;
import com.slusarz.pokerafterdark.specification.api.Profit;
import com.slusarz.pokerafterdark.spring.common.delivery.query.QueryMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetProfitsMapper implements QueryMapper<GetProfitRequest, ProfitQuery, ProfitQueryResult, GetProfitResponse> {

    @Override
    public ProfitQuery toQuery(GetProfitRequest request) {
        List<PlayerId> playerIds = request.getPlayersIds().stream().map(PlayerId::of).collect(Collectors.toList());
        List<GameType> gameTypes = request.getGameTypes().stream().map(Enum::name).map(GameType::valueOf).collect(Collectors.toList());
        return ProfitQuery.of(request.getFrom(), request.getTo(), gameTypes, playerIds);
    }

    @Override
    public GetProfitResponse toResponse(ProfitQueryResult result) {
        return GetProfitResponse.builder()
                .profits(result.getProfit().stream().map(this::toProfit).collect(Collectors.toList()))
                .build();
    }

    private Profit toProfit(com.slusarz.pokerafterdark.domain.profit.Profit profit) {
        return Profit.builder()
                .playerId(profit.getPlayerId().getId())
                .playerName(profit.getPlayerName().getName())
                .dataPoints(profit.getWinnings().stream().map(Earnings::getValue).map(BigDecimal::new).collect(Collectors.toList()))
                .build();
    }

}
