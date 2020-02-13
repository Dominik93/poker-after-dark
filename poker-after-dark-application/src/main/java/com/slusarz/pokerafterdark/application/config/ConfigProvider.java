package com.slusarz.pokerafterdark.application.config;

import java.time.LocalDate;

public interface ConfigProvider {

    Integer getEntryMoney();

    Integer getEntryChips();

    LocalDate getPagesFrom();

    LocalDate getPagesTo();
}
