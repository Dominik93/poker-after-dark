package com.slusarz.pokerafterdark.spring.configuration.persistence;

import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.application.livewinnings.LiveWinningsRepository;
import com.slusarz.pokerafterdark.application.player.PlayerQueryRepository;
import com.slusarz.pokerafterdark.application.profit.ProfitQueryRepository;
import com.slusarz.pokerafterdark.application.profit.ProfitRepository;
import com.slusarz.pokerafterdark.application.usecase.addgame.GameRepository;
import com.slusarz.pokerafterdark.application.usecase.createplayer.PlayerRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ParticipationEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ProfitEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameQueryCaller;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameQueryJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.livewinnings.LiveWinningsJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.player.PlayerJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.player.PlayerQueryJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.profit.ProfitJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.profit.ProfitQueryJpaRepository;
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
