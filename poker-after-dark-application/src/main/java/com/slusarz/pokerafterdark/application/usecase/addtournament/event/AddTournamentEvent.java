package com.slusarz.pokerafterdark.application.usecase.addtournament.event;

import com.slusarz.pokerafterdark.application.events.Event;
import com.slusarz.pokerafterdark.domain.tournament.Tournament;
import lombok.Value;

@Value(staticConstructor = "of")
public class AddTournamentEvent implements Event {

    private Tournament tournament;

}
