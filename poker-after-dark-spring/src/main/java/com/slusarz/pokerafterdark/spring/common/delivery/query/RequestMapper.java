package com.slusarz.pokerafterdark.spring.common.delivery.query;

import com.slusarz.pokerafterdark.application.common.cqrs.query.Query;

public interface RequestMapper<REQUEST, QUERY extends Query> {

    QUERY toQuery(REQUEST request);

}
