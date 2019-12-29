package com.slusarz.pokerafterdark.spring.delivery.mapper.query;

import com.slusarz.pokerafterdark.aplication.cqrs.query.QueryResult;

public interface ResultMapper<QUERY_RESULT extends QueryResult, RESPONSE> {

    RESPONSE toResponse(QUERY_RESULT queryResult);

}
