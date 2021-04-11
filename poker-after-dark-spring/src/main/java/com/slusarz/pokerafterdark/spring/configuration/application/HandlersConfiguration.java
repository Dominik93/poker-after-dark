package com.slusarz.pokerafterdark.spring.configuration.application;

import com.slusarz.pokerafterdark.application.common.events.EventBus;
import com.slusarz.pokerafterdark.application.config.ConfigProvider;
import com.slusarz.pokerafterdark.application.config.ConfigQueryHandler;
import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.application.game.GamesQueryHandler;
import com.slusarz.pokerafterdark.application.player.PlayerQueryHandler;
import com.slusarz.pokerafterdark.application.player.PlayerQueryRepository;
import com.slusarz.pokerafterdark.application.players.PlayersQueryHandler;
import com.slusarz.pokerafterdark.application.players.PlayersQueryRepository;
import com.slusarz.pokerafterdark.application.profit.ProfitQueryHandler;
import com.slusarz.pokerafterdark.application.profit.ProfitQueryRepository;
import com.slusarz.pokerafterdark.application.usecase.addcashgame.AddCashGameCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.addgame.validator.AddGameValidator;
import com.slusarz.pokerafterdark.application.usecase.addtournament.AddTournamentCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.createplayer.validator.CreatePlayerValidator;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommandHandler;
import com.slusarz.pokerafterdark.application.usecase.removegame.validator.RemoveGameValidator;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameRepository;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import com.slusarz.pokerafterdark.domain.player.PlayerRepository;
import com.slusarz.pokerafterdark.domain.tournament.TournamentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlersConfiguration {

    @Bean
    public AddTournamentCommandHandler addTournamentCommandHandler(@Qualifier("afterCommitEventBus") EventBus eventsBus,
                                                                   TournamentRepository tournamentRepository,
                                                                   AddGameValidator addGameValidator) {
        return new AddTournamentCommandHandler(eventsBus, tournamentRepository, addGameValidator);
    }

    @Bean
    public CreatePlayerCommandHandler createPlayerCommandHandler(@Qualifier("afterCommitEventBus") EventBus eventsBus,
                                                                 CreatePlayerValidator createPlayerValidator,
                                                                 PlayerRepository playerRepository){
        return new CreatePlayerCommandHandler(playerRepository, createPlayerValidator, eventsBus);
    }

    @Bean
    public RemoveGameCommandHandler removeGameCommandHandler(@Qualifier("afterCommitEventBus") EventBus eventsBus,
                                                             GameRepository gameRepository,
                                                             RemoveGameValidator removeGameValidator){
        return new RemoveGameCommandHandler(eventsBus, gameRepository, removeGameValidator);
    }

    @Bean
    public AddCashGameCommandHandler addGameCommandHandler(@Qualifier("afterCommitEventBus")EventBus eventsBus,
                                                           CashGameRepository cashGameRepository,
                                                           AddGameValidator addGameValidator){
        return new AddCashGameCommandHandler(eventsBus, cashGameRepository, addGameValidator);
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
    public PlayersQueryHandler playersQueryHandler(PlayersQueryRepository playersQueryRepository){
        return new PlayersQueryHandler(playersQueryRepository);
    }

    @Bean
    public PlayerQueryHandler playerQueryHandler(PlayerQueryRepository playerQueryRepository){
        return new PlayerQueryHandler(playerQueryRepository);
    }

    @Bean
    public ProfitQueryHandler profitQueryHandler(ProfitQueryRepository profitQueryRepository,
                                                 ConfigProvider configProvider) {
        return new ProfitQueryHandler(profitQueryRepository, configProvider);
    }

}
