package com.slusarz.pokerafterdark.domain.game;

public interface GameRepository {

    void remove(GameId gameId);

    Game readLast();

}
