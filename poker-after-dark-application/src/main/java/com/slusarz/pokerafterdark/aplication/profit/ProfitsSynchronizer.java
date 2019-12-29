package com.slusarz.pokerafterdark.aplication.profit;

import com.slusarz.pokerafterdark.aplication.usecase.createplayer.PlayerRepository;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.profit.Profit;
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

    public void synchronize(Game game) {
        Map<PlayerId, Participant> participants = game.getParticipants().stream()
                .collect(Collectors.toMap(Participant::getPlayerId, Function.identity()));

        int gameNumber = profitJpaRepository.getGameNumber();

        for (Player player : playerJpaRepository.readPlayers(participants.keySet())) {
            addWin(participants.get(player.getPlayerId()), player, game, gameNumber);
        }
    }

    private void addWin(Participant participant, Player player, Game game, int gameNumber) {
        log.info("Participant [" + participant + "], Player [" + player + "], Game [" + game + "], game [" + gameNumber + "]");
        Profit profit = profitJpaRepository.getProfit(player.getPlayerId());
        Earnings earnings = getEarnings(profit);
        log.info("Earnings [" + earnings + "]");
        Earnings win = getWin(participant, earnings);
        log.info("Add win [" + win + "] to player [" + player.getPlayerName() + "]");
        profitJpaRepository.add(game, profit, win, gameNumber);
    }

    private Earnings getEarnings(Profit profit) {
        if (profit.getWinnings().isEmpty()) {
            return Earnings.of(0);
        } else {
            return profit.getWinnings().get(profit.getWinnings().size() - 1);

        }
    }

    private Earnings getWin(Participant participant, Earnings earnings) {
        if (Objects.isNull(participant)) {
            return Earnings.of(earnings.getValue());
        } else {
            return Earnings.of(earnings.getValue() + participant.getEarnings().getValue());
        }
    }


}
