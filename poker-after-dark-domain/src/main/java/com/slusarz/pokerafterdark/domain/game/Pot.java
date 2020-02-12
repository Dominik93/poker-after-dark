package com.slusarz.pokerafterdark.domain.game;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.constraints.Min;

@Value(staticConstructor = "of")
public class Pot {

    @Min(value = 0, message = ValidationError.POT_BELOW_ZERO)
    private int value;

}
