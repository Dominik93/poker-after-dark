package com.slusarz.pokerafterdark.domain.profit;

import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.player.PlayerId;

public interface ProfitRepository {

    int getGameNumber();

    Profit getProfit(PlayerId playerId);

    void add(Game cashGame, Profit profit, Earnings win, int gameNumber);
}
