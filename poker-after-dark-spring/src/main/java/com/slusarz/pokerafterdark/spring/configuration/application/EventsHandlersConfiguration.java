package com.slusarz.pokerafterdark.spring.configuration.application;

import com.slusarz.pokerafterdark.application.livewinnings.LiveWinningsSynchronizer;
import com.slusarz.pokerafterdark.application.livewinnings.PlayerProjectionRepository;
import com.slusarz.pokerafterdark.application.profit.ProfitsSynchronizer;
import com.slusarz.pokerafterdark.application.usecase.addcashgame.event.AddCashGameEventHandler;
import com.slusarz.pokerafterdark.application.usecase.addtournament.event.AddTournamentEventHandler;
import com.slusarz.pokerafterdark.application.usecase.removegame.event.RemoveGameEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventsHandlersConfiguration {

    @Bean
    public AddCashGameEventHandler addGameEventHandler(LiveWinningsSynchronizer winningsSynchronizer,
                                                       ProfitsSynchronizer profitsSynchronizer) {
        return new AddCashGameEventHandler(winningsSynchronizer, profitsSynchronizer);
    }

    @Bean
    public AddTournamentEventHandler addTournamentEventHandler(LiveWinningsSynchronizer winningsSynchronizer,
                                                               ProfitsSynchronizer profitsSynchronizer) {
        return new AddTournamentEventHandler(winningsSynchronizer, profitsSynchronizer);
    }

    @Bean
    public RemoveGameEventHandler removeGameEventHandler(PlayerProjectionRepository playerProjectionRepository) {
        return new RemoveGameEventHandler(playerProjectionRepository);
    }

}
