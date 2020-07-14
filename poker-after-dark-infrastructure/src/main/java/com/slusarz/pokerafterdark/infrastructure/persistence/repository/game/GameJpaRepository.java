package com.slusarz.pokerafterdark.infrastructure.persistence.repository.game;

import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.game.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.GameEntityMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@NoArgsConstructor
@AllArgsConstructor
public class GameJpaRepository implements GameRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private GameCaller gameCaller;

    private GameEntityMapper gameEntityMapper;

    public GameJpaRepository(GameCaller gameCaller,
                             GameEntityMapper gameEntityMapper) {
        this.gameCaller = gameCaller;
        this.gameEntityMapper = gameEntityMapper;
    }

    @Override
    public void remove(GameId gameId) {
        GameJpaEntity gameJpaEntity = entityManager.find(GameJpaEntity.class, gameId.getId());
        entityManager.remove(gameJpaEntity);
    }

    @Override
    public Game readLast() {
        return gameEntityMapper.toGame(gameCaller.readLast());
    }

}
