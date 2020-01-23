package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.game.GameProjection;
import com.slusarz.pokerafterdark.application.game.GamesQuery;
import com.slusarz.pokerafterdark.application.game.GamesQueryResult;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.specification.api.Game;
import com.slusarz.pokerafterdark.specification.api.GetGamesRequest;
import com.slusarz.pokerafterdark.specification.api.GetGamesResponse;
import com.slusarz.pokerafterdark.specification.api.Host;
import com.slusarz.pokerafterdark.spring.delivery.mapper.query.QueryMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetGamesMapper implements QueryMapper<GetGamesRequest, GamesQuery, GamesQueryResult, GetGamesResponse> {

    @Override
    public GamesQuery toQuery(GetGamesRequest getGamesRequest) {
        return toGamesQuery(getGamesRequest);
    }

    @Override
    public GetGamesResponse toResponse(GamesQueryResult queryResult) {
        return toGetGamesResponse(queryResult.getGames());
    }

    private GetGamesResponse toGetGamesResponse(List<GameProjection> games) {
        return GetGamesResponse.builder().games(toGames(games)).build();
    }

    private List<Game> toGames(List<GameProjection> games) {
        return games.stream().map(this::toGame).collect(Collectors.toList());
    }

    private Game toGame(GameProjection game) {
        return Game.builder().date(game.getDate())
                .host(toHost(game))
                .id(game.getGameId().getId())
                .participantsCount(game.getParticipants().size())
                .pot(game.getPot().getValue())
                .participants(toParticipants(game.getParticipants()))
                .build();
    }

    private Host toHost(GameProjection game) {
        return Host.builder()
                .id(game.getHost().getPlayerId().getId())
                .name(game.getHost().getPlayerName().getName())
                .build();
    }

    private List<com.slusarz.pokerafterdark.specification.api.Participant> toParticipants(List<Participant> participants) {
        return participants.stream().map(this::toParticipant).collect(Collectors.toList());
    }

    private com.slusarz.pokerafterdark.specification.api.Participant toParticipant(Participant participant) {
        return com.slusarz.pokerafterdark.specification.api.Participant.builder()
                .playerId(participant.getPlayerId().getId())
                .earnings(new BigDecimal(participant.getEarnings().getValue()))
                .build();
    }

    private GamesQuery toGamesQuery(GetGamesRequest request) {
        return GamesQuery.of(request.getFrom(),
                request.getTo(),
                request.getPlayersIds().stream().map(PlayerId::of).collect(Collectors.toList()));
    }


}
