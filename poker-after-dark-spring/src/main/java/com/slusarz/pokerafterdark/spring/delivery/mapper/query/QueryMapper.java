package com.slusarz.pokerafterdark.spring.delivery.mapper.query;

import com.slusarz.pokerafterdark.application.cqrs.query.Query;
import com.slusarz.pokerafterdark.application.cqrs.query.QueryResult;

public interface QueryMapper<REQUEST, QUERY extends Query, QUERY_RESULT extends QueryResult, RESPONSE>
        extends RequestMapper<REQUEST, QUERY>, ResultMapper<QUERY_RESULT, RESPONSE> {

}
