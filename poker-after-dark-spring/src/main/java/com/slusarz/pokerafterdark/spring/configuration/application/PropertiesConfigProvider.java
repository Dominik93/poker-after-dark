package com.slusarz.pokerafterdark.spring.configuration.application;

import com.slusarz.pokerafterdark.application.config.ConfigProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Optional;

@Configuration
public class PropertiesConfigProvider implements ConfigProvider {

    @Value("${poker-after-dark.entry.chips}")
    private Integer entryChips;

    @Value("${poker-after-dark.entry.money}")
    private Integer entryMoney;

    @Value("${poker-after-dark.pages.from}")
    private String pagesFrom;

    @Value("${poker-after-dark.pages.to:#{null}}")
    private String pagesTo;

    @Override
    public Integer getEntryMoney() {
        return entryMoney;
    }

    @Override
    public Integer getEntryChips() {
        return entryChips;
    }

    @Override
    public LocalDate getPagesFrom() {
        return LocalDate.parse(pagesFrom);
    }

    @Override
    public LocalDate getPagesTo() {
        return Optional.ofNullable(pagesTo).map(LocalDate::parse).orElse(LocalDate.now());
    }
}
