package com.slusarz.pokerafterdark.specification.model.profit;


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
public class GetProfitRequest {

    private LocalDate from;

    private LocalDate to;

    private List<String> playersIds;

}
