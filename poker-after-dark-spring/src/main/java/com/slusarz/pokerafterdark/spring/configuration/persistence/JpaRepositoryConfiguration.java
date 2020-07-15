package com.slusarz.pokerafterdark.spring.configuration.persistence;

import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.application.livewinnings.PlayerProjectionRepository;
import com.slusarz.pokerafterdark.application.player.PlayerQueryRepository;
import com.slusarz.pokerafterdark.application.players.PlayersQueryRepository;
import com.slusarz.pokerafterdark.application.profit.ProfitQueryRepository;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameRepository;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import com.slusarz.pokerafterdark.domain.player.PlayerRepository;
import com.slusarz.pokerafterdark.domain.profit.ProfitRepository;
import com.slusarz.pokerafterdark.domain.tournament.TournamentRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.CashGameEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameProjectionEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ParticipationEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ProfitEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.TournamentEntityMapper;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.cashgame.CashGameJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameCaller;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameQueryCaller;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.GameQueryJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.player.PlayerJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.player.PlayerProjectionJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.player.PlayerQueryJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.players.PlayersQueryJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.profit.ProfitJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.profit.ProfitQueryJpaRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.repository.tournament.TournamentJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaRepositoryConfiguration {

    @Bean
    public TournamentRepository tournamentRepository(TournamentEntityMapper tournamentEntityMapper,
                                                     ParticipationEntityMapper participationEntityMapper){
        return new TournamentJpaRepository(tournamentEntityMapper, participationEntityMapper);
    }

    @Bean
    public PlayerProjectionRepository playerProjectionRepository() {
        return new PlayerProjectionJpaRepository();
    }

    @Bean
    public CashGameRepository cashGameRepository(CashGameEntityMapper cashGameEntityMapper,
                                                 ParticipationEntityMapper participationEntityMapper) {
        return new CashGameJpaRepository(cashGameEntityMapper, participationEntityMapper);
    }

    @Bean
    public GameRepository gameRepository(GameCaller gameCaller, GameEntityMapper gameEntityMapper) {
        return new GameJpaRepository(gameCaller,gameEntityMapper);
    }

    @Bean
    public GameQueryRepository gameQueryRepository(GameQueryCaller gameQueryCaller,
                                                   GameCaller gameCaller,
                                                   GameProjectionEntityMapper gameProjectionEntityMapper) {
        return new GameQueryJpaRepository(gameQueryCaller, gameCaller, gameProjectionEntityMapper);
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
    public PlayersQueryRepository playersQueryRepository(PlayerEntityMapper playerEntityMapper) {
        return new PlayersQueryJpaRepository(playerEntityMapper);
    }

    @Bean
    public GameCaller gameCaller() {
        return new GameCaller();
    }

    @Bean
    public GameQueryCaller gameQueryCaller() {
        return new GameQueryCaller();
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
    public TournamentEntityMapper tournamentEntityMapper() {
        return new TournamentEntityMapper();
    }

    @Bean
    public CashGameEntityMapper cashGameEntityMapper() {
        return new CashGameEntityMapper();
    }

    @Bean
    public GameEntityMapper gameEntityMapper() {
        return new GameEntityMapper();
    }

    @Bean
    public GameProjectionEntityMapper gameProjectionEntityMapper() {
        return new GameProjectionEntityMapper();
    }

}
