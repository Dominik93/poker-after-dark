package com.slusarz.pokerafterdark.spring.configuration.application;

import com.slusarz.pokerafterdark.application.config.ConfigQueryHandler;
import com.slusarz.pokerafterdark.application.config.ConfigProvider;
import com.slusarz.pokerafterdark.application.events.EventBus;
import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.application.game.GamesQueryHandler;
import com.slusarz.pokerafterdark.application.player.PlayerQueryRepository;
import com.slusarz.pokerafterdark.application.player.PlayersQueryHandler;
import com.slusarz.pokerafterdark.application.profit.ProfitQueryHandler;
import com.slusarz.pokerafterdark.application.profit.ProfitQueryRepository;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameValidator;
import com.slusarz.pokerafterdark.application.usecase.addgame.GameRepository;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerValidator;
import com.slusarz.pokerafterdark.application.usecase.createplayer.PlayerRepository;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommandHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlersConfiguration {

    @Bean
    public CreatePlayerCommandHandler createPlayerCommandHandler(@Qualifier("afterCommitEventBus") EventBus eventsBus,
                                                                 CreatePlayerValidator createPlayerValidator,
                                                                 PlayerRepository playerRepository){
        return new CreatePlayerCommandHandler(playerRepository, createPlayerValidator, eventsBus);
    }

    @Bean
    public RemoveGameCommandHandler removeGameCommandHandler(@Qualifier("afterCommitEventBus") EventBus eventsBus,
                                                             GameRepository gameRepository){
        return new RemoveGameCommandHandler(eventsBus, gameRepository);
    }

    @Bean
    public AddGameCommandHandler addGameCommandHandler(@Qualifier("afterCommitEventBus")EventBus eventsBus,
                                                       GameRepository gameRepository,
                                                       AddGameValidator addGameValidator){
        return new AddGameCommandHandler(eventsBus, gameRepository, addGameValidator);
    }

    @Bean
    public ConfigQueryHandler configQueryHandler(ConfigProvider configProvider){
        return new ConfigQueryHandler(configProvider);
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
