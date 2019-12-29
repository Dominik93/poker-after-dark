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
public class GetGamesRequest {

    private LocalDate from;

    private LocalDate to;

    private List<String> playersIds;

}
