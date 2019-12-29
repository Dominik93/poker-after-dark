package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.aplication.config.ConfigQueryResult;
import com.slusarz.pokerafterdark.domain.config.EntryFee;
import com.slusarz.pokerafterdark.domain.config.ListFilter;
import com.slusarz.pokerafterdark.specification.model.config.Config;
import com.slusarz.pokerafterdark.specification.model.config.GetConfigResponse;
import com.slusarz.pokerafterdark.spring.delivery.mapper.query.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ConfigMapper implements ResultMapper<ConfigQueryResult, GetConfigResponse> {

    @Override
    public GetConfigResponse toResponse(ConfigQueryResult result) {
        return toGetConfigResponse(result.getConfig());
    }

    private GetConfigResponse toGetConfigResponse(com.slusarz.pokerafterdark.domain.config.Config config) {
        return GetConfigResponse.builder().config(toConfig(config.getEntryFee(), config.getListFilter())).build();
    }

    private Config toConfig(EntryFee entryFee, ListFilter listFilter) {
        return Config.builder()
                .entryFeeInChips(entryFee.getChips())
                .entryFeeInMoney(entryFee.getMoney())
                .from(listFilter.getFrom())
                .to(listFilter.getTo())
                .build();
    }
}
