package com.slusarz.pokerafterdark.domain.tournament;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.constraints.Min;

@Value(staticConstructor = "of")
public class Place {

    @Min(value = 0, message = ValidationError.NEGATIVE_PLACE)
    private int place;

}
