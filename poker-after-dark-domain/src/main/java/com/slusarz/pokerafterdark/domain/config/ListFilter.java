package com.slusarz.pokerafterdark.domain.config;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value(staticConstructor = "of")
public class ListFilter {

    @NotNull
    private LocalDate from;

    @NotNull
    private LocalDate to;

}
