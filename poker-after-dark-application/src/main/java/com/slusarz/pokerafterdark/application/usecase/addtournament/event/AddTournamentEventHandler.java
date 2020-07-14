package com.slusarz.pokerafterdark.application.usecase.addtournament.event;

import com.slusarz.pokerafterdark.application.events.EventHandler;
import com.slusarz.pokerafterdark.application.livewinnings.LiveWinningsSynchronizer;
import com.slusarz.pokerafterdark.application.profit.ProfitsSynchronizer;
import com.slusarz.pokerafterdark.domain.tournament.Tournament;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class AddTournamentEventHandler implements EventHandler<AddTournamentEvent> {

    private LiveWinningsSynchronizer liveWinningsSynchronizer;

    private ProfitsSynchronizer profitsSynchronizer;

    @Override
    public void handle(AddTournamentEvent event) {
        Tournament tournament = event.getTournament();
        liveWinningsSynchronizer.synchronize(tournament);
        profitsSynchronizer.synchronize(tournament);
    }

    @Override
    public Class getEvent() {
        return AddTournamentEvent.class;
    }
}
