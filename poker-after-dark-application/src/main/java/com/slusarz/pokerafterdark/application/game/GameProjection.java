package com.slusarz.pokerafterdark.application.game;

import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.GameType;
import com.slusarz.pokerafterdark.domain.player.Host;
import com.slusarz.pokerafterdark.domain.pot.Pot;
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

    @NonNull
    private GameActions actions;

    @NonNull
    private GameType gameType;

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
    private List<ParticipantProjection> participantProjections;
}
