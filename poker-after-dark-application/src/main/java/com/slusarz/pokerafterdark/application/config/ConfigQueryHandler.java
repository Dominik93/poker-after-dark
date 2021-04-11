package com.slusarz.pokerafterdark.application.config;

import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryHandler;
import com.slusarz.pokerafterdark.domain.config.Config;
import com.slusarz.pokerafterdark.domain.config.EntryFee;
import com.slusarz.pokerafterdark.domain.config.ListFilter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfigQueryHandler implements QueryHandler<ConfigQueryResult, ConfigQuery> {

    private ConfigProvider configProvider;

    @Override
    public ConfigQueryResult handle(ConfigQuery command) {
        EntryFee entryFee = EntryFee.of(configProvider.getEntryChips(), configProvider.getEntryMoney());
        ListFilter listFilter = ListFilter.of(configProvider.getPagesFrom(), configProvider.getPagesTo());
        return ConfigQueryResult.of(Config.of(entryFee, listFilter));
    }
}
