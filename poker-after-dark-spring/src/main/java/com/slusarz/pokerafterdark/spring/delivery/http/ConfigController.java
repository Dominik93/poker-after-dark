package com.slusarz.pokerafterdark.spring.delivery.http;

import com.slusarz.pokerafterdark.aplication.config.ConfigQuery;
import com.slusarz.pokerafterdark.aplication.config.ConfigQueryResult;
import com.slusarz.pokerafterdark.aplication.cqrs.ServiceExecutor;
import com.slusarz.pokerafterdark.specification.model.config.GetConfigResponse;
import com.slusarz.pokerafterdark.spring.delivery.mapper.ConfigMapper;
import com.slusarz.pokerafterdark.spring.delivery.request.QueryRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConfigController.CONFIG_PATH)
public class ConfigController {

    final static String CONFIG_PATH = "config";

    @Autowired
    private ServiceExecutor serviceExecutor;

    @Autowired
    private ConfigMapper configMapper;

    @GetMapping
    public GetConfigResponse getConfig() {
        return new QueryRequestHandler<GetConfigResponse, Void, ConfigQueryResult>
                (serviceExecutor, configMapper, aVoid -> ConfigQuery.of())
                .handle(null);

    }

}
