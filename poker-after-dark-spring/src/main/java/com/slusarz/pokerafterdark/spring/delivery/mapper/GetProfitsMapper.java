package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.aplication.profit.ProfitQuery;
import com.slusarz.pokerafterdark.aplication.profit.ProfitQueryResult;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.specification.model.profit.GetProfitRequest;
import com.slusarz.pokerafterdark.specification.model.profit.GetProfitResponse;
import com.slusarz.pokerafterdark.specification.model.profit.Profit;
import com.slusarz.pokerafterdark.spring.delivery.mapper.query.QueryMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetProfitsMapper implements QueryMapper<GetProfitRequest, ProfitQuery, ProfitQueryResult, GetProfitResponse> {

    @Override
    public ProfitQuery toQuery(GetProfitRequest request) {
        List<PlayerId> playerIds = request.getPlayersIds().stream().map(PlayerId::of).collect(Collectors.toList());
        return ProfitQuery.of(request.getFrom(), request.getTo(), playerIds);
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
                .dataPoints(profit.getWinnings().stream().map(Earnings::getValue).collect(Collectors.toList()))
                .build();
    }

}
