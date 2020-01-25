package com.slusarz.pokerafterdark.spring.configuration.application;

import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.application.livewinnings.LiveWinningsSynchronizer;
import com.slusarz.pokerafterdark.application.profit.ProfitRepository;
import com.slusarz.pokerafterdark.application.profit.ProfitsSynchronizer;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameValidator;
import com.slusarz.pokerafterdark.application.usecase.createplayer.PlayerRepository;
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
    public AddGameValidator addGameValidator(GameQueryRepository gameQueryRepository) {
        return new AddGameValidator(gameQueryRepository);
    }

}
