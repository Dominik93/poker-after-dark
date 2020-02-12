package com.slusarz.pokerafterdark.domain.config;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value(staticConstructor = "of")
public class ListFilter {

    @NotNull(message = ValidationError.MANDATORY_PAGES_FILTER_FROM)
    private LocalDate from;

    @NotNull(message = ValidationError.MANDATORY_PAGES_FILTER_TO)
    private LocalDate to;

}
