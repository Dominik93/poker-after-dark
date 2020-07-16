package com.slusarz.pokerafterdark.spring.common.cqrs;

import com.slusarz.pokerafterdark.application.common.cqrs.handler.HandlerProvider;
import com.slusarz.pokerafterdark.application.common.cqrs.query.Query;
import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryExecutor;
import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SimpleQueryExecutor implements QueryExecutor {

    private HandlerProvider<QueryResult, Query> handlerProvider;

    public QueryResult execute(Query query) {
        log.info("Execute query [" + query + "]");
        long ttime = System.currentTimeMillis();
        QueryResult queryResult = handlerProvider.getHandler(query).handle(query);
        log.info("Query executed in " + (System.currentTimeMillis() - ttime) + "ms, result = [" + queryResult + "]");
        return queryResult;
    }

}
