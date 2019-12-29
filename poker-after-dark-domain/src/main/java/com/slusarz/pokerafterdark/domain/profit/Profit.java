package com.slusarz.pokerafterdark.domain.profit;

import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value(staticConstructor = "of")
public class Profit {

    @Valid
    @NotNull
    private PlayerId playerId;

    @Valid
    @NotNull
    private PlayerName playerName;

    @Valid
    private List<Earnings> winnings;
}
