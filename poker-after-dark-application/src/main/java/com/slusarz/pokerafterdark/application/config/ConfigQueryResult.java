package com.slusarz.pokerafterdark.application.config;

import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryResult;
import com.slusarz.pokerafterdark.domain.config.Config;
import lombok.Value;

@Value(staticConstructor = "of")
public class ConfigQueryResult implements QueryResult {

    private Config config;

}
