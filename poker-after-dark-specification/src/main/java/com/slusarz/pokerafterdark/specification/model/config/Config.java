package com.slusarz.pokerafterdark.specification.model.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Config {

    private Integer entryFeeInChips;

    private Integer entryFeeInMoney;

    private LocalDate from;

    private LocalDate to;

}
