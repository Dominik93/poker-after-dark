package com.slusarz.pokerafterdark.application.game;

import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.Host;
import com.slusarz.pokerafterdark.domain.game.Pot;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value(staticConstructor = "of")
public class GameProjection {

    private GameId gameId;

    private Host host;

    private LocalDate date;

    private Pot pot;

    private List<Participant> participants;
}
