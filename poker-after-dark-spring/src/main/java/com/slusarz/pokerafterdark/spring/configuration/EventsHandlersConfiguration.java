package com.slusarz.pokerafterdark.spring.configuration;

import com.slusarz.pokerafterdark.aplication.livewinnings.LiveWinningsRepository;
import com.slusarz.pokerafterdark.aplication.livewinnings.LiveWinningsSynchronizer;
import com.slusarz.pokerafterdark.aplication.profit.ProfitsSynchronizer;
import com.slusarz.pokerafterdark.aplication.usecase.addgame.event.AddGameEventHandler;
import com.slusarz.pokerafterdark.aplication.usecase.removegame.event.RemoveGameEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventsHandlersConfiguration {

    @Bean
    public AddGameEventHandler addGameEventHandler(LiveWinningsSynchronizer liveWinningsSynchronizer,
                                                   ProfitsSynchronizer profitsSynchronizer) {
        return new AddGameEventHandler(liveWinningsSynchronizer, profitsSynchronizer);
    }

    @Bean
    public RemoveGameEventHandler removeGameEventHandler(LiveWinningsRepository liveWinningsJpaRepository) {
        return new RemoveGameEventHandler(liveWinningsJpaRepository);
    }

}
