package com.slusarz.pokerafterdark.spring.cqrs;

import com.slusarz.pokerafterdark.application.cqrs.handler.HandlerProvider;
import com.slusarz.pokerafterdark.application.cqrs.query.QueryHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return queryHandlers.get(query.getClass());
    }

}
