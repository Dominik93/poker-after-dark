package com.slusarz.pokerafterdark.aplication.cqrs.query;

public interface QueryExecutor {

    QueryResult execute(Query query);

}
