package com.slusarz.pokerafterdark.domain.tournament;


import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.domain.validation.CorrectPlaces;
import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import com.slusarz.pokerafterdark.domain.validation.ValidationExecutor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tournament {

    @Valid
    @NotNull(message = ValidationError.MANDATORY_TOURNAMENT_ID)
    private TournamentId tournamentId;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_HOST_ID)
    private PlayerId host;

    @NotNull(message = ValidationError.MANDATORY_GAME_DATE)
    private LocalDate occurrenceDate;

    @Valid
    @CorrectPlaces(message = ValidationError.INVALID_TOURNAMENT_PARTICIPANTS)
    @NotEmpty(message = ValidationError.EMPTY_TOURNAMENT_PARTICIPANTS)
    private List<TournamentParticipant> participants;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_POT)
    private Pot pot;

    public static Tournament of(TournamentId tournamentId,
                                List<TournamentParticipant> participants,
                                PlayerId host,
                                LocalDate occurrenceDate,
                                Pot pot) {
        Tournament tournament = new Tournament(tournamentId, host, occurrenceDate, participants, pot);
        return ValidationExecutor.validateAndReturn(tournament);
    }
}
