package com.slusarz.pokerafterdark.application.config;

import com.slusarz.pokerafterdark.application.cqrs.query.QueryHandler;
import com.slusarz.pokerafterdark.domain.config.Config;
import com.slusarz.pokerafterdark.domain.config.EntryFee;
import com.slusarz.pokerafterdark.domain.config.ListFilter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class ConfigQueryHandler implements QueryHandler<ConfigQueryResult, ConfigQuery> {

    @Override
    public ConfigQueryResult handle(ConfigQuery command) {
        EntryFee entryFee = EntryFee.of(1000, 10);
        ListFilter listFilter = ListFilter.of(LocalDate.now().minusYears(20), LocalDate.now());
        return ConfigQueryResult.of(Config.of(entryFee, listFilter));
    }
}
