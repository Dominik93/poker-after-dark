package com.slusarz.pokerafterdark.spring.common.delivery.query;

import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryResult;

public interface ResultMapper<QUERY_RESULT extends QueryResult, RESPONSE> {

    RESPONSE toResponse(QUERY_RESULT queryResult);

}
