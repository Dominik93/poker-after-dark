package com.slusarz.pokerafterdark.aplication.config;

import com.slusarz.pokerafterdark.aplication.cqrs.query.QueryResult;
import com.slusarz.pokerafterdark.domain.config.Config;
import lombok.Value;

@Value(staticConstructor = "of")
public class ConfigQueryResult implements QueryResult {

    private Config config;

}
