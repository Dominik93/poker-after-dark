package com.slusarz.pokerafterdark.aplication.profit;

import com.slusarz.pokerafterdark.aplication.cqrs.query.QueryResult;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class ProfitQueryResult implements QueryResult {

    private List<Profit> profit;

}
