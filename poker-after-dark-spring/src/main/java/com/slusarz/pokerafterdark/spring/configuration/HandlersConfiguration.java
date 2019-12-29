package com.slusarz.pokerafterdark.spring.configuration;

import com.slusarz.pokerafterdark.aplication.config.ConfigQueryHandler;
import com.slusarz.pokerafterdark.aplication.events.EventBus;
import com.slusarz.pokerafterdark.aplication.game.GameQueryRepository;
import com.slusarz.pokerafterdark.aplication.game.GamesQueryHandler;
import com.slusarz.pokerafterdark.aplication.player.PlayerQueryRepository;
import com.slusarz.pokerafterdark.aplication.player.PlayersQueryHandler;
import com.slusarz.pokerafterdark.aplication.profit.ProfitQueryHandler;
import com.slusarz.pokerafterdark.aplication.profit.ProfitQueryRepository;
import com.slusarz.pokerafterdark.aplication.usecase.addgame.AddGameCommandHandler;
import com.slusarz.pokerafterdark.aplication.usecase.addgame.GameRepository;
import com.slusarz.pokerafterdark.aplication.usecase.createplayer.CreatePlayerCommandHandler;
import com.slusarz.pokerafterdark.aplication.usecase.createplayer.PlayerRepository;
import com.slusarz.pokerafterdark.aplication.usecase.removegame.RemoveGameCommandHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlersConfiguration {

    @Bean
    public CreatePlayerCommandHandler createPlayerCommandHandler(PlayerRepository playerRepository){
        return new CreatePlayerCommandHandler(playerRepository);
    }

    @Bean
    public RemoveGameCommandHandler removeGameCommandHandler(@Qualifier("afterCommitEventBus") EventBus eventsBus, GameRepository gameRepository){
        return new RemoveGameCommandHandler(eventsBus, gameRepository);
    }

    @Bean
    public AddGameCommandHandler addGameCommandHandler(@Qualifier("afterCommitEventBus")EventBus eventsBus, GameRepository gameRepository){
        return new AddGameCommandHandler(eventsBus, gameRepository);
    }

    @Bean
    public ConfigQueryHandler configQueryHandler(){
        return new ConfigQueryHandler();
    }

    @Bean
    public GamesQueryHandler gamesQueryHandler(GameQueryRepository gameRepository){
        return new GamesQueryHandler(gameRepository);
    }

    @Bean
    public PlayersQueryHandler playersQueryHandler(PlayerQueryRepository playerQueryRepository){
        return new PlayersQueryHandler(playerQueryRepository);
    }

    @Bean
    public ProfitQueryHandler profitQueryHandler(ProfitQueryRepository profitQueryRepository) {
        return new ProfitQueryHandler(profitQueryRepository);
    }



}
