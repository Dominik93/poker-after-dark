package com.slusarz.pokerafterdark.spring.delivery.request;

import com.slusarz.pokerafterdark.application.common.cqrs.ServiceExecutor;
import com.slusarz.pokerafterdark.application.common.cqrs.query.Query;
import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryResult;
import com.slusarz.pokerafterdark.spring.common.delivery.query.QueryMapper;
import com.slusarz.pokerafterdark.spring.common.delivery.query.RequestMapper;
import com.slusarz.pokerafterdark.spring.common.delivery.query.ResultMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryRequestHandler<RESPONSE, REQUEST, QUERY_RESULT extends QueryResult>  {

    private ServiceExecutor serviceExecutor;

    private ResultMapper responseMapper;

    private RequestMapper queryMapper;

    public QueryRequestHandler(ServiceExecutor serviceExecutor, QueryMapper queryMapper) {
        this.serviceExecutor = serviceExecutor;
        this.responseMapper = queryMapper;
        this.queryMapper = queryMapper;
    }

    public RESPONSE handle(REQUEST request) {
        Query query = queryMapper.toQuery(request);
        QUERY_RESULT queryResult = (QUERY_RESULT) serviceExecutor.executeQuery(query);
        return (RESPONSE) responseMapper.toResponse(queryResult);
    }

}
