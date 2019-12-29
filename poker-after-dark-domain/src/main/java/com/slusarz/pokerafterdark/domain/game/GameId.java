package com.slusarz.pokerafterdark.domain.game;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class GameId {

    @NotNull
    private String id;

}
