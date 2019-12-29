package com.slusarz.pokerafterdark.domain.player;

import lombok.Value;

import javax.validation.constraints.Min;

@Value(staticConstructor = "of")
public class NumberOfPlays {

    @Min(0)
    private int value;

}
