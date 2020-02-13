package com.slusarz.pokerafterdark.domain.participant;

import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class Participant {

    @Valid
    @NotNull(message = ValidationError.MANDATORY_PLAYER_ID)
    private PlayerId playerId;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_EARNINGS)
    private Earnings earnings;

}
