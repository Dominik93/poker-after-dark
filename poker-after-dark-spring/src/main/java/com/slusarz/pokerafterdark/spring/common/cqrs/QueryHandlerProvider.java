package com.slusarz.pokerafterdark.spring.common.cqrs;

import com.slusarz.pokerafterdark.application.common.cqrs.handler.HandlerProvider;
import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryHandler;
import com.slusarz.pokerafterdark.spring.common.cqrs.exceptions.ExecutionException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class QueryHandlerProvider implements HandlerProvider {

    private Map<Class, QueryHandler> queryHandlers = new HashMap<>();

    public QueryHandlerProvider(List<QueryHandler> queryHandlers) {
        for (QueryHandler queryHandler : queryHandlers) {
            try {
                String handler = HandlerNameUtil.getHandlerClassName(queryHandler.getClass());
                this.queryHandlers.put(Class.forName(handler), queryHandler);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public QueryHandler getHandler(Object query) {
        QueryHandler queryHandler = queryHandlers.get(query.getClass());
        if (Objects.isNull(queryHandler) ){
            throw new ExecutionException(query);
        }
        return queryHandler;
    }

}
