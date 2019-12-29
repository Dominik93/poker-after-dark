package com.slusarz.pokerafterdark.specification.model.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private String id;

    private String name;

    private int gamesPlayed;

    private double liveEarnings;

    private double biggestWin;

    private double biggestLose;

}
