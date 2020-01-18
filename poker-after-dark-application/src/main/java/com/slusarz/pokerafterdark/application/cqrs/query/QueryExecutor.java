package com.slusarz.pokerafterdark.application.cqrs.query;

public interface QueryExecutor {

    QueryResult execute(Query query);

}
