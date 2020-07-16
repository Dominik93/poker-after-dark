package com.slusarz.pokerafterdark.application.common.cqrs.query;

public interface QueryExecutor {

    QueryResult execute(Query query);

}
