package com.slusarz.pokerafterdark.domain.game;

import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Value(staticConstructor = "of")
public class Host {

    @Valid
    @NotNull
    private PlayerId playerId;

    @Valid
    @NotNull
    private PlayerName playerName;

}
