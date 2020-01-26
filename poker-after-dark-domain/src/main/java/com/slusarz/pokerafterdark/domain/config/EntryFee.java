package com.slusarz.pokerafterdark.domain.config;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class EntryFee {

    @Min(value = 0, message = ValidationError.MANDATORY_ENTRY_FEE_CHIPS)
    private Integer chips;

    @Min(value = 0, message = ValidationError.MANDATORY_ENTRY_FEE_MONEY)
    private Integer money;

}
