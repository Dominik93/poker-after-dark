package com.slusarz.pokerafterdark.application.usecase.addgame.event;

import com.slusarz.pokerafterdark.application.events.EventHandler;
import com.slusarz.pokerafterdark.application.livewinnings.LiveWinningsSynchronizer;
import com.slusarz.pokerafterdark.application.profit.ProfitsSynchronizer;
import com.slusarz.pokerafterdark.domain.game.Game;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class AddGameEventHandler implements EventHandler<AddGameEvent> {

    private LiveWinningsSynchronizer liveWinningsSynchronizer;

    private ProfitsSynchronizer profitsSynchronizer;

    @Override
    public void handle(AddGameEvent event) {
        Game game = event.getGame();
        liveWinningsSynchronizer.synchronize(game);
        profitsSynchronizer.synchronize(game);
    }

    @Override
    public Class getEvent() {
        return AddGameEvent.class;
    }
}
