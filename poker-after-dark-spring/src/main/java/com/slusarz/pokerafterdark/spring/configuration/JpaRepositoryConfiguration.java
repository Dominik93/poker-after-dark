package com.slusarz.pokerafterdark.spring.configuration;

import com.slusarz.pokerafterdark.aplication.game.GameQueryRepository;
import com.slusarz.pokerafterdark.aplication.livewinnings.LiveWinningsRepository;
import com.slusarz.pokerafterdark.aplication.player.PlayerQueryRepository;
import com.slusarz.pokerafterdark.aplication.profit.ProfitQueryRepository;
import com.slusarz.pokerafterdark.aplication.profit.ProfitRepository;
import com.slusarz.pokerafterdark.aplication.usecase.addgame.GameRepository;
import com.slusarz.pokerafterdark.aplication.usecase.createplayer.PlayerRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ParticipationEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ProfitEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.GameJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.GameQueryCaller;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.GameQueryJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.LiveWinningsJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.PlayerJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.PlayerQueryJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.ProfitJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.ProfitQueryJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaRepositoryConfiguration {

    @Bean
    public LiveWinningsRepository liveWinningsRepository() {
        return new LiveWinningsJpaRepository();
    }

    @Bean
    public GameRepository gameRepository(GameEntityMapper gameEntityMapper, ParticipationEntityMapper participationEntityMapper) {
        return new GameJpaRepository(gameEntityMapper, participationEntityMapper);
    }

    @Bean
    public GameQueryRepository gameQueryRepository(GameQueryCaller gameQueryCaller, GameEntityMapper gameEntityMapper) {
        return new GameQueryJpaRepository(gameQueryCaller, gameEntityMapper);
    }

    @Bean
    public PlayerRepository playerRepository(PlayerEntityMapper playerEntityMapper) {
        return new PlayerJpaRepository(playerEntityMapper);
    }

    @Bean
    public ProfitRepository profitRepository(ProfitEntityMapper profitEntityMapper) {
        return new ProfitJpaRepository(profitEntityMapper);
    }

    @Bean
    public ProfitQueryRepository profitQueryRepositorypository(ProfitEntityMapper profitEntityMapper) {
        return new ProfitQueryJpaRepository(profitEntityMapper);
    }


    @Bean
    public PlayerQueryRepository playerQueryRepository(PlayerEntityMapper playerEntityMapper) {
        return new PlayerQueryJpaRepository(playerEntityMapper);
    }

    @Bean
    public ProfitEntityMapper profitEntityMapper() {
        return new ProfitEntityMapper();
    }

    @Bean
    public PlayerEntityMapper playerEntityMapper() {
        return new PlayerEntityMapper();
    }

    @Bean
    public ParticipationEntityMapper participationEntityMapper() {
        return new ParticipationEntityMapper();
    }

    @Bean
    public GameEntityMapper gameEntityMapper(ParticipationEntityMapper participationEntityMapper) {
        return new GameEntityMapper(participationEntityMapper);
    }


    @Bean
    public GameQueryCaller gameQueryCaller() {
        return new GameQueryCaller();
    }
}
