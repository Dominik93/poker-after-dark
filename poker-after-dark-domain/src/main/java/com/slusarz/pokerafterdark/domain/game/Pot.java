package com.slusarz.pokerafterdark.domain.game;

import lombok.Value;

import javax.validation.constraints.Min;

@Value(staticConstructor = "of")
public class Pot {

    @Min(0)
    private int value;

}
