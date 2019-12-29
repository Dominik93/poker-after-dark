package com.slusarz.pokerafterdark.domain.participant;

import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class Participant {

    @Valid
    @NotNull
    private PlayerId playerId;

    @Valid
    @NotNull
    private Earnings earnings;

}
