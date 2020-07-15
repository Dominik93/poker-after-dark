package com.slusarz.pokerafterdark.domain.tournament;

public interface TournamentRepository {

    TournamentId generateId();

    void save(Tournament tournament);

}
