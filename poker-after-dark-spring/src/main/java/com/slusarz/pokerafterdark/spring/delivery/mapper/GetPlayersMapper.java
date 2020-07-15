package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.players.PlayersQueryResult;
import com.slusarz.pokerafterdark.specification.api.GetPlayersResponse;
import com.slusarz.pokerafterdark.spring.delivery.mapper.query.ResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GetPlayersMapper implements ResultMapper<PlayersQueryResult, GetPlayersResponse> {

    @Autowired
    private PlayersMapper playersMapper;

    @Override
    public GetPlayersResponse toResponse(PlayersQueryResult queryResult) {
        return GetPlayersResponse.builder()
                .players(playersMapper.toPlayers(queryResult.getPlayers()))
                .build();
    }

}