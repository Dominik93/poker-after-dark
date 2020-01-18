package com.slusarz.pokerafterdark.spring.delivery.http;

import com.slusarz.pokerafterdark.application.cqrs.ServiceExecutor;
import com.slusarz.pokerafterdark.application.player.PlayersQuery;
import com.slusarz.pokerafterdark.application.player.PlayersQueryResult;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommandResult;
import com.slusarz.pokerafterdark.specification.model.player.AddPlayerRequest;
import com.slusarz.pokerafterdark.specification.model.player.AddPlayerResponse;
import com.slusarz.pokerafterdark.specification.model.player.GetPlayersResponse;
import com.slusarz.pokerafterdark.spring.delivery.mapper.AddPlayerMapper;
import com.slusarz.pokerafterdark.spring.delivery.mapper.GetPlayersMapper;
import com.slusarz.pokerafterdark.spring.delivery.request.CommandRequestHandler;
import com.slusarz.pokerafterdark.spring.delivery.request.QueryRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PlayersController.PLAYERS_PATH)
public class PlayersController {

    final static String PLAYERS_PATH = "players";

    @Autowired
    private ServiceExecutor serviceExecutor;

    @Autowired
    private AddPlayerMapper addPlayerMapper;

    @Autowired
    private GetPlayersMapper getPlayersMapper;

    @PostMapping
    public AddPlayerResponse addPlayer(@RequestBody AddPlayerRequest addPlayerRequest) {
        return new CommandRequestHandler<AddPlayerResponse, AddPlayerRequest, CreatePlayerCommandResult>
                (serviceExecutor, addPlayerMapper).handle(addPlayerRequest);
    }

    @GetMapping
    public GetPlayersResponse getPlayers() {
        return new QueryRequestHandler<GetPlayersResponse, Void, PlayersQueryResult>
                (serviceExecutor, getPlayersMapper, aVoid -> PlayersQuery.of())
                .handle(null);
    }

}
