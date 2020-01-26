package com.slusarz.pokerafterdark.domain.config;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import com.slusarz.pokerafterdark.domain.validation.ValidationExecutor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Config {

    @Valid
    @NotNull(message = ValidationError.MANDATORY_ENTRY_FEE)
    private EntryFee entryFee;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_PAGES_FILTER)
    private ListFilter listFilter;

    public static Config of(EntryFee entryFee, ListFilter listFilter) {
        return ValidationExecutor.validateAndReturn(new Config(entryFee, listFilter));
    }
}
