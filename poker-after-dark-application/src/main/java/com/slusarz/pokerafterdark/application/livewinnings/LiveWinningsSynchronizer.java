package com.slusarz.pokerafterdark.application.livewinnings;

import com.slusarz.pokerafterdark.domain.cashgame.CashGame;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameParticipant;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerRepository;
import com.slusarz.pokerafterdark.domain.tournament.Tournament;
import com.slusarz.pokerafterdark.domain.tournament.TournamentParticipant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class LiveWinningsSynchronizer {

    private PlayerRepository playerJpaRepository;

    public void synchronize(CashGame cashGame) {
        Map<PlayerId, CashGameParticipant> participants = cashGame.getCashGameParticipants().stream()
                .collect(Collectors.toMap(CashGameParticipant::getPlayerId, Function.identity()));
        List<Player> players = playerJpaRepository.readPlayers(participants.keySet());
        for (Player player : players) {
            CashGameParticipant cashGameParticipant = participants.get(player.getPlayerId());
            addWin(player, cashGameParticipant);
        }
        playerJpaRepository.save(players);
    }

    private void addWin(Player player, CashGameParticipant cashGameParticipant) {
        log.info("Add [" + cashGameParticipant.getEarnings() + "] to [" + player + "]");
        player.addWin(cashGameParticipant.getEarnings().getValue());
    }


    public void synchronize(Tournament cashGame) {
        Map<PlayerId, TournamentParticipant> participants = cashGame.getParticipants().stream()
                .collect(Collectors.toMap(TournamentParticipant::getPlayerId, Function.identity()));
        List<Player> players = playerJpaRepository.readPlayers(participants.keySet());
        for (Player player : players) {
            TournamentParticipant cashGameParticipant = participants.get(player.getPlayerId());
            addWin(player, cashGameParticipant);
        }
        playerJpaRepository.save(players);
    }

    private void addWin(Player player, TournamentParticipant cashGameParticipant) {
        log.info("Add [" + cashGameParticipant.getEarnings() + "] to [" + player + "]");
        player.addWin(cashGameParticipant.getEarnings().getValue());
    }

}
