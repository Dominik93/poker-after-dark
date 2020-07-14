package com.slusarz.pokerafterdark.application.profit;

import com.slusarz.pokerafterdark.domain.cashgame.CashGame;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameParticipant;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerRepository;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import com.slusarz.pokerafterdark.domain.profit.ProfitRepository;
import com.slusarz.pokerafterdark.domain.tournament.Tournament;
import com.slusarz.pokerafterdark.domain.tournament.TournamentParticipant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class ProfitsSynchronizer {

    private ProfitRepository profitJpaRepository;

    private PlayerRepository playerJpaRepository;

    public void synchronize(CashGame cashGame) {
        Map<PlayerId, CashGameParticipant> participants = cashGame.getCashGameParticipants().stream()
                .collect(Collectors.toMap(CashGameParticipant::getPlayerId, Function.identity()));

        int gameNumber = profitJpaRepository.getGameNumber();

        for (Player player : playerJpaRepository.readPlayers(participants.keySet())) {
            addWin(participants.get(player.getPlayerId()), player, cashGame, gameNumber);
        }
    }

    private void addWin(CashGameParticipant cashGameParticipant, Player player, CashGame cashGame, int gameNumber) {
        log.info("CashGameParticipant [" + cashGameParticipant + "], Player [" + player + "], CashGame [" + cashGame + "], cashGame [" + gameNumber + "]");
        Profit profit = profitJpaRepository.getProfit(player.getPlayerId());
        Earnings earnings = getEarnings(profit);
        log.info("Earnings [" + earnings + "]");
        Earnings win = getWin(cashGameParticipant, earnings);
        log.info("Add win [" + win + "] to player [" + player.getPlayerName() + "]");
        Game game = Game.of(GameId.of(cashGame.getCashGameId().getId()), cashGame.getHost(), cashGame.getOccurrenceDate(), cashGame.getPot());

        profitJpaRepository.add(game, profit, win, gameNumber);
    }

    private Earnings getEarnings(Profit profit) {
        if (profit.getWinnings().isEmpty()) {
            return Earnings.zero();
        } else {
            return profit.getWinnings().get(profit.getWinnings().size() - 1);
        }
    }

    private Earnings getWin(CashGameParticipant cashGameParticipant, Earnings earnings) {
        if (Objects.isNull(cashGameParticipant)) {
            return Earnings.of(earnings.getValue());
        } else {
            return Earnings.of(earnings.getValue() + cashGameParticipant.getEarnings().getValue());
        }
    }






    public void synchronize(Tournament cashGame) {
        Map<PlayerId, TournamentParticipant> participants = cashGame.getParticipants().stream()
                .collect(Collectors.toMap(TournamentParticipant::getPlayerId, Function.identity()));

        int gameNumber = profitJpaRepository.getGameNumber();

        for (Player player : playerJpaRepository.readPlayers(participants.keySet())) {
            addWin(participants.get(player.getPlayerId()), player, cashGame, gameNumber);
        }
    }

    private void addWin(TournamentParticipant cashGameParticipant, Player player, Tournament cashGame, int gameNumber) {
        log.info("CashGameParticipant [" + cashGameParticipant + "], Player [" + player + "], CashGame [" + cashGame + "], cashGame [" + gameNumber + "]");
        Profit profit = profitJpaRepository.getProfit(player.getPlayerId());
        Earnings earnings = getEarnings(profit);
        log.info("Earnings [" + earnings + "]");
        Earnings win = getWin(cashGameParticipant, earnings);
        log.info("Add win [" + win + "] to player [" + player.getPlayerName() + "]");
        Game game = Game.of(GameId.of(cashGame.getTournamentId().getId()), cashGame.getHost(), cashGame.getOccurrenceDate(), cashGame.getPot());
        profitJpaRepository.add(game, profit, win, gameNumber);
    }


    private Earnings getWin(TournamentParticipant cashGameParticipant, Earnings earnings) {
        if (Objects.isNull(cashGameParticipant)) {
            return Earnings.of(earnings.getValue());
        } else {
            return Earnings.of(earnings.getValue() + cashGameParticipant.getEarnings().getValue());
        }
    }



}
