package com.slusarz.pokerafterdark.domain.cashgame;

public interface CashGameRepository {

    CashGameId generateId();

    void save(CashGame cashGame);

}
