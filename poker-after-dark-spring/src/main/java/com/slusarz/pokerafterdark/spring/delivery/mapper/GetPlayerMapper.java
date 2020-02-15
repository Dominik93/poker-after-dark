package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.player.PlayerQueryResult;
import com.slusarz.pokerafterdark.specification.api.GetPlayerResponse;
import com.slusarz.pokerafterdark.spring.delivery.mapper.query.ResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GetPlayerMapper implements ResultMapper<PlayerQueryResult, GetPlayerResponse> {

    @Autowired
    private PlayersMapper playersMapper;

    @Override
    public GetPlayerResponse toResponse(PlayerQueryResult queryResult) {
        return GetPlayerResponse.of( playersMapper.toPlayer(queryResult.getPlayer()));
    }
}