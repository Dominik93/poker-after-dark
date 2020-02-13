package com.slusarz.pokerafterdark.spring.delivery.http;

import com.slusarz.pokerafterdark.application.cqrs.ServiceExecutor;
import com.slusarz.pokerafterdark.application.game.GamesQueryResult;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameCommandResult;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommandResult;
import com.slusarz.pokerafterdark.specification.api.AddGameRequest;
import com.slusarz.pokerafterdark.specification.api.AddGameResponse;
import com.slusarz.pokerafterdark.specification.api.GetGamesRequest;
import com.slusarz.pokerafterdark.specification.api.GetGamesResponse;
import com.slusarz.pokerafterdark.specification.api.RemoveGameResponse;
import com.slusarz.pokerafterdark.spring.delivery.mapper.AddGameMapper;
import com.slusarz.pokerafterdark.spring.delivery.mapper.GetGamesMapper;
import com.slusarz.pokerafterdark.spring.delivery.mapper.RemoveGameMapper;
import com.slusarz.pokerafterdark.spring.delivery.request.CommandRequestHandler;
import com.slusarz.pokerafterdark.spring.delivery.request.QueryRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GamesController.GAMES_PATH)
public class GamesController {

    final static String GAMES_PATH= "games";

    @Autowired
    private ServiceExecutor serviceExecutor;

    @Autowired
    private AddGameMapper addGameMapper;

    @Autowired
    private RemoveGameMapper removeGameMapper;

    @Autowired
    private GetGamesMapper getGamesMapper;

    @PostMapping("/pages")
    public GetGamesResponse getGames(@RequestBody GetGamesRequest request) {
        return new QueryRequestHandler<GetGamesResponse, GetGamesRequest, GamesQueryResult>
                (serviceExecutor, getGamesMapper).handle(request);
    }

    @PostMapping
    public AddGameResponse addGame(@RequestBody AddGameRequest addGameRequest) {
        return new CommandRequestHandler<AddGameResponse, AddGameRequest, AddGameCommandResult>
                (serviceExecutor, addGameMapper).handle(addGameRequest);
    }

    @DeleteMapping("/{id}")
    public RemoveGameResponse removeGame(@PathVariable("id") String gameId) {
        return new CommandRequestHandler<RemoveGameResponse, String, RemoveGameCommandResult>
                (serviceExecutor, removeGameMapper, removeGameMapper).handle(gameId);
    }

}
