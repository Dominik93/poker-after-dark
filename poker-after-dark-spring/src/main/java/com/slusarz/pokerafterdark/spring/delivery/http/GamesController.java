package com.slusarz.pokerafterdark.spring.delivery.http;

import com.slusarz.pokerafterdark.application.common.cqrs.ServiceExecutor;
import com.slusarz.pokerafterdark.application.game.GamesQueryResult;
import com.slusarz.pokerafterdark.application.usecase.addcashgame.AddCashGameCommandResult;
import com.slusarz.pokerafterdark.application.usecase.addtournament.AddTournamentCommandResult;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommandResult;
import com.slusarz.pokerafterdark.specification.api.AddCachGameRequest;
import com.slusarz.pokerafterdark.specification.api.AddGameResponse;
import com.slusarz.pokerafterdark.specification.api.AddTournamentRequest;
import com.slusarz.pokerafterdark.specification.api.GetGamesRequest;
import com.slusarz.pokerafterdark.specification.api.GetGamesResponse;
import com.slusarz.pokerafterdark.specification.api.RemoveGameResponse;
import com.slusarz.pokerafterdark.spring.delivery.mapper.AddCashGameMapper;
import com.slusarz.pokerafterdark.spring.delivery.mapper.AddTournamentMapper;
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

    final static String GAMES_PATH = "games";

    @Autowired
    private ServiceExecutor serviceExecutor;

    @Autowired
    private AddCashGameMapper addCashGameMapper;

    @Autowired
    private AddTournamentMapper addTournamentMapper;

    @Autowired
    private RemoveGameMapper removeGameMapper;

    @Autowired
    private GetGamesMapper getGamesMapper;

    @PostMapping("/pages")
    public GetGamesResponse getGames(@RequestBody GetGamesRequest request) {
        return new QueryRequestHandler<GetGamesResponse, GetGamesRequest, GamesQueryResult>
                (serviceExecutor, getGamesMapper).handle(request);
    }

    @PostMapping("/cash")
    public AddGameResponse addCashGame(@RequestBody AddCachGameRequest addCachGameRequest) {
        return new CommandRequestHandler<AddGameResponse, AddCachGameRequest, AddCashGameCommandResult>
                (serviceExecutor, addCashGameMapper).handle(addCachGameRequest);
    }

    @PostMapping("/tournament")
    public AddGameResponse addTournament(@RequestBody AddTournamentRequest addTournamentRequest) {
        return new CommandRequestHandler<AddGameResponse, AddTournamentRequest, AddTournamentCommandResult>
                (serviceExecutor, addTournamentMapper).handle(addTournamentRequest);
    }

    @DeleteMapping("/{id}")
    public RemoveGameResponse removeGame(@PathVariable("id") String gameId) {
        return new CommandRequestHandler<RemoveGameResponse, String, RemoveGameCommandResult>
                (serviceExecutor, removeGameMapper, removeGameMapper).handle(gameId);
    }

}
