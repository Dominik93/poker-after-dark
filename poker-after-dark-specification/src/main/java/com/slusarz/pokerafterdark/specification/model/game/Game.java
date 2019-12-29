package com.slusarz.pokerafterdark.specification.model.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private String id;

    private Host host;

    private LocalDate date;

    private int pot;

    private int participantsCount;

    private List<Participant> participants;

}
