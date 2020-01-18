package com.slusarz.pokerafterdark.application.cqrs.query;

import com.slusarz.pokerafterdark.application.cqrs.handler.Handler;

public interface QueryHandler<RESULT extends QueryResult, QUERY_TYPE extends Query> extends Handler<RESULT, QUERY_TYPE> {

    RESULT handle(QUERY_TYPE query);
}
