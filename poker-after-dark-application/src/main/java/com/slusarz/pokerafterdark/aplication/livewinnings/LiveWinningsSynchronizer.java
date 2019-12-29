package com.slusarz.pokerafterdark.aplication.livewinnings;

import com.slusarz.pokerafterdark.aplication.usecase.createplayer.PlayerRepository;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
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

    public void synchronize(Game game) {
        Map<PlayerId, Participant> participants = game.getParticipants().stream()
                .collect(Collectors.toMap(Participant::getPlayerId, Function.identity()));
        List<Player> players = playerJpaRepository.readPlayers(participants.keySet());
        for (Player player : players) {
            Participant participant = participants.get(player.getPlayerId());
            addWin(player, participant);
        }
        playerJpaRepository.save(players);
    }

    private void addWin(Player player, Participant participant) {
        log.info("Add [" + participant.getEarnings() + "] to [" + player + "]");
        player.addWin(participant.getEarnings().getValue());
    }

}
