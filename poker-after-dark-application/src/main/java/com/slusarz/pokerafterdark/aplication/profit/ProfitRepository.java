package com.slusarz.pokerafterdark.aplication.profit;

import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.profit.Profit;

public interface ProfitRepository {

    int getGameNumber();

    Profit getProfit(PlayerId playerId);

    void add(Game game, Profit profit, Earnings win, int gameNumber);
}
