package com.slusarz.pokerafterdark.application.profit;

import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryResult;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class ProfitQueryResult implements QueryResult {

    private List<Profit> profit;

}
