package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.game.GameProjection;
import com.slusarz.pokerafterdark.application.game.GamesQuery;
import com.slusarz.pokerafterdark.application.game.GamesQueryResult;
import com.slusarz.pokerafterdark.application.game.ParticipantProjection;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.tournament.Place;
import com.slusarz.pokerafterdark.specification.api.Game;
import com.slusarz.pokerafterdark.specification.api.GameActions;
import com.slusarz.pokerafterdark.specification.api.GetGamesRequest;
import com.slusarz.pokerafterdark.specification.api.GetGamesResponse;
import com.slusarz.pokerafterdark.specification.api.Host;
import com.slusarz.pokerafterdark.spring.common.delivery.query.QueryMapper;
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
                .actions(GameActions.builder()
                        .remove(game.getActions().isRemove())
                        .build())
                .type(Game.TypeEnum.valueOf(game.getGameType().name()))
                .participantsCount(game.getParticipantProjections().size())
                .pot(game.getPot().getValue())
                .participants(toParticipants(game.getParticipantProjections()))
                .build();
    }

    private Host toHost(GameProjection game) {
        return Host.builder()
                .id(game.getHost().getPlayerId().getId())
                .name(game.getHost().getPlayerName().getName())
                .build();
    }

    private List<com.slusarz.pokerafterdark.specification.api.Participant> toParticipants(
            List<ParticipantProjection> participantProjections) {
        return participantProjections.stream().map(this::toParticipant).collect(Collectors.toList());
    }

    private com.slusarz.pokerafterdark.specification.api.Participant toParticipant(ParticipantProjection participantProjection) {
        return com.slusarz.pokerafterdark.specification.api.Participant.builder()
                .playerId(participantProjection.getPlayerId().getId())
                .playerName(participantProjection.getPlayerName().getName())
                .earnings(new BigDecimal(participantProjection.getEarnings().getValue()))
                .place(participantProjection.getPlace().map(Place::getPlace).orElse(null))
                .build();
    }

    private GamesQuery toGamesQuery(GetGamesRequest request) {
        return GamesQuery.of(request.getFrom(),
                request.getTo(),
                request.getPlayersIds().stream().map(PlayerId::of).collect(Collectors.toList()));
    }


}
