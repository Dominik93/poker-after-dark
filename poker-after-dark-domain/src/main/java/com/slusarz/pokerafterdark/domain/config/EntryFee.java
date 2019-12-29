package com.slusarz.pokerafterdark.domain.config;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class EntryFee {

    @NotNull
    @Min(0)
    private Integer chips;

    @NotNull
    @Min(0)
    private Integer money;

}
