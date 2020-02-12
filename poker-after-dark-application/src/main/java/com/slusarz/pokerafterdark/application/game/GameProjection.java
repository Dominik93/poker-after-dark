package com.slusarz.pokerafterdark.application.game;

import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.Host;
import com.slusarz.pokerafterdark.domain.game.Pot;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import lombok.NonNull;
import lombok.Value;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Value(staticConstructor = "of")
public class GameProjection {

    @Valid
    @NonNull
    private GameId gameId;

    @Valid
    @NonNull
    private Host host;

    @Valid
    @NonNull
    private LocalDate date;

    @Valid
    @NonNull
    private Pot pot;

    @Valid
    @NonNull
    private List<Participant> participants;
}
