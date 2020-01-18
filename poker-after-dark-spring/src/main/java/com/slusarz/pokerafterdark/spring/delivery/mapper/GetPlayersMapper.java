package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.player.PlayerProjection;
import com.slusarz.pokerafterdark.application.player.PlayersQueryResult;
import com.slusarz.pokerafterdark.specification.model.player.GetPlayersResponse;
import com.slusarz.pokerafterdark.specification.model.player.Player;
import com.slusarz.pokerafterdark.spring.delivery.mapper.query.ResultMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class GetPlayersMapper implements ResultMapper<PlayersQueryResult, GetPlayersResponse> {

    @Override
    public GetPlayersResponse toResponse(PlayersQueryResult queryResult) {
        return GetPlayersResponse.builder()
                .players(toPlayers(queryResult.getPlayers()))
                .build();
    }

    private List<Player> toPlayers(List<PlayerProjection> players) {
        return players.stream().map(this::toPlayer).collect(Collectors.toList());
    }

    private Player toPlayer(PlayerProjection player) {
        return Player.builder()
                .id(player.getPlayerId().getId())
                .name(player.getPlayerName().getName())
                .biggestWin(round(player.getBiggestWin().getValue(), 2))
                .biggestLose(round(player.getBiggestLose().getValue(), 2))
                .gamesPlayed(player.getNumberOfPlays().getValue())
                .liveEarnings(round(player.getLiveEarnings().getValue(), 2))
                .build();
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        return BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

}