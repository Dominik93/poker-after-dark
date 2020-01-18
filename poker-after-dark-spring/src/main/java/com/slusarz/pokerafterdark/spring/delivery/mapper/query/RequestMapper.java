package com.slusarz.pokerafterdark.spring.delivery.mapper.query;

import com.slusarz.pokerafterdark.application.cqrs.query.Query;

public interface RequestMapper<REQUEST, QUERY extends Query> {

    QUERY toQuery(REQUEST request);

}
