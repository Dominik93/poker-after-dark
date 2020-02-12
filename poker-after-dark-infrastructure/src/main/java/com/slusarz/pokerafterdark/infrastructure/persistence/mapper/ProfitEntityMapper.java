package com.slusarz.pokerafterdark.infrastructure.persistence.mapper;

import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import com.slusarz.pokerafterdark.infrastructure.persistence.common.Pair;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.GameJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.ProfitJpaEntity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ProfitEntityMapper {

    private static final int PLAYER_NAME_INDEX = 0;
    private static final int LP_INDEX = 1;
    private static final int PROFIT_INDEX = 2;

    public Profit toProfit(PlayerJpaEntity playerJpaEntity) {
        return Profit.of(playerJpaEntity.getPlayerId(), playerJpaEntity.getPlayerName(),
                playerJpaEntity.getProfits().stream()
                        .sorted(Comparator.comparingInt(ProfitJpaEntity::getLp))
                        .map(ProfitJpaEntity::getProfit)
                        .map(Earnings::of)
                        .collect(Collectors.toList()));
    }

    public ProfitJpaEntity toProfitEntity(GameJpaEntity gameJpaEntity, double win, int maxLp) {
        int lp = maxLp + 1;
        return ProfitJpaEntity.builder()
                .lp(lp)
                .date(LocalDate.now())
                .profit(win)
                .game(gameJpaEntity)
                .build();
    }

    public Map<PlayerName, List<Pair<Integer, Earnings>>> toProfitMap(List<Object[]> userProfits) {
        Map<PlayerName, List<Pair<Integer, Earnings>>> profitMap = new HashMap<>();
        for (Object[] userProfit : userProfits) {
            PlayerName playerName = PlayerName.of((String) userProfit[PLAYER_NAME_INDEX]);
            Double profit = (Double) userProfit[PROFIT_INDEX];
            Integer lp = Integer.valueOf(userProfit[LP_INDEX].toString());
            List<Pair<Integer, Earnings>> earnings = profitMap.get(playerName);
            Pair<Integer, Earnings> pair = new Pair<>(lp, Earnings.of(profit));
            if (Objects.isNull(earnings)) {
                profitMap.put(playerName, new ArrayList<>(Arrays.asList(new Pair<>(0, Earnings.zero()), pair)));
            } else {
                earnings.add(pair);
            }

        }
        return profitMap;
    }

    public Profit toProfit(PlayerJpaEntity playerJpaEntity, int gameNumber, Map<PlayerName, List<Pair<Integer, Earnings>>> profitMap) {
        List<Earnings> earnings = toEarnings(gameNumber, profitMap.getOrDefault(playerJpaEntity.getPlayerName(), new ArrayList<>()));
        return Profit.of(playerJpaEntity.getPlayerId(), playerJpaEntity.getPlayerName(), earnings);
    }

    private List<Earnings> toEarnings(int gameNumber, List<Pair<Integer, Earnings>> list) {
        List<Earnings> earnings = new ArrayList<>();
        Map<Integer, Earnings> collect = list.stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue));
        for(int i = 0; i <= gameNumber; i++) {
            earnings.add(collect.getOrDefault(i, getLastItem(earnings)));
        }
        return earnings;
    }

    private Earnings getLastItem(List<Earnings> earnings) {
        if (earnings.isEmpty()) {
            return Earnings.zero();
        }
        return earnings.get(earnings.size() - 1);
    }
}
