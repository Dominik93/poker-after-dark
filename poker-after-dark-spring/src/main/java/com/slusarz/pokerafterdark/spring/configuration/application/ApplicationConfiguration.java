package com.slusarz.pokerafterdark.spring.configuration.application;

import com.slusarz.pokerafterdark.application.livewinnings.LiveWinningsSynchronizer;
import com.slusarz.pokerafterdark.application.profit.ProfitsSynchronizer;
import com.slusarz.pokerafterdark.application.usecase.addgame.validator.AddGameValidator;
import com.slusarz.pokerafterdark.application.usecase.createplayer.validator.CreatePlayerValidator;
import com.slusarz.pokerafterdark.application.usecase.removegame.validator.RemoveGameValidator;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import com.slusarz.pokerafterdark.domain.player.PlayerRepository;
import com.slusarz.pokerafterdark.domain.profit.ProfitRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public LiveWinningsSynchronizer liveWinningsSynchronizer(PlayerRepository playerJpaRepository) {
        return new LiveWinningsSynchronizer(playerJpaRepository);
    }

    @Bean
    public ProfitsSynchronizer profitJpaRepository(PlayerRepository playerJpaRepository, ProfitRepository profitJpaRepository) {
        return new ProfitsSynchronizer(profitJpaRepository, playerJpaRepository);
    }

    @Bean
    public AddGameValidator addCashGameValidator(GameRepository addGameValidator) {
        return new AddGameValidator(addGameValidator);
    }

    @Bean
    public CreatePlayerValidator createPlayerValidator(PlayerRepository playerRepository) {
        return new CreatePlayerValidator(playerRepository);
    }

    @Bean
    public RemoveGameValidator removeGameValidator(GameRepository gameRepository) {
        return new RemoveGameValidator(gameRepository);
    }

}
