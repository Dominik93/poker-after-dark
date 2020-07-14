package com.slusarz.pokerafterdark.application.usecase.addcashgame.event;

import com.slusarz.pokerafterdark.application.events.EventHandler;
import com.slusarz.pokerafterdark.application.livewinnings.LiveWinningsSynchronizer;
import com.slusarz.pokerafterdark.application.profit.ProfitsSynchronizer;
import com.slusarz.pokerafterdark.domain.cashgame.CashGame;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class AddCashGameEventHandler implements EventHandler<AddCashGameEvent> {

    private LiveWinningsSynchronizer liveWinningsSynchronizer;

    private ProfitsSynchronizer profitsSynchronizer;

    @Override
    public void handle(AddCashGameEvent event) {
        CashGame cashGame = event.getCashGame();
        liveWinningsSynchronizer.synchronize(cashGame);
        profitsSynchronizer.synchronize(cashGame);
    }

    @Override
    public Class getEvent() {
        return AddCashGameEvent.class;
    }
}
