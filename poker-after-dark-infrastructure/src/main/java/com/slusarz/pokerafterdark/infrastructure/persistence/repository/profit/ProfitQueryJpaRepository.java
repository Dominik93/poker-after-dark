package com.slusarz.pokerafterdark.infrastructure.persistence.repository.profit;

import com.slusarz.pokerafterdark.application.profit.ProfitQueryRepository;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.profit.Profit;
import com.slusarz.pokerafterdark.infrastructure.persistence.common.Pair;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.ProfitEntityMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ProfitQueryJpaRepository implements ProfitQueryRepository {
    private static final String SELECT_PROFITS_BETWEEN_DATE = "select pl.name, p.lp, p.profit from PROFIT p " +
            "join PLAYER pl on pl.id = p.player_id " +
            "where pl.id in ( :playerIds ) and p.date BETWEEN :startDate AND :endDate order by p.date asc";
    private static final String SELECT_PROFITS_BETWEEN_DATE_EMPTY_PLAYERS = "select pl.name, p.lp, p.profit from PROFIT p " +
            "join PLAYER pl on pl.id = p.player_id " +
            "where p.date BETWEEN :startDate AND :endDate order by p.date asc";
    private static final String SELECT_PLAYERS_BY_IDS = "select p from PlayerJpaEntity p where p.id in ( :playerIds )";
    private static final String SELECT_PLAYERS = "select p from PlayerJpaEntity p";
    private static final String START_DATE_PARAM = "startDate";
    private static final String END_DATE_PARAM = "endDate";
    private static final String PLAYER_IDS_PARAM = "playerIds";

    private static final String SELECT_MAX_LP = "select max(p.lp) from ProfitJpaEntity p";

    private final Map<Boolean, String> selectProfits = new HashMap<Boolean, String>() {{
        put(true, SELECT_PROFITS_BETWEEN_DATE_EMPTY_PLAYERS);
        put(false, SELECT_PROFITS_BETWEEN_DATE);
    }};

    private final Map<Boolean, String> selectPlayers = new HashMap<Boolean, String>() {{
        put(true, SELECT_PLAYERS);
        put(false, SELECT_PLAYERS_BY_IDS);
    }};

    @PersistenceContext
    private EntityManager entityManager;

    private ProfitEntityMapper profitEntityMapper;

    public ProfitQueryJpaRepository(ProfitEntityMapper profitEntityMapper) {
        this.profitEntityMapper = profitEntityMapper;
    }

    @Override
    public List<Profit> getProfits(LocalDate from, LocalDate to, List<PlayerId> playerIds) {
        List<Object[]> userProfits = getUserProfit(from, to, playerIds);
        int gameNumber = (int) entityManager.createQuery(SELECT_MAX_LP).getSingleResult();
        Map<PlayerName, List<Pair<Integer, Earnings>>> profitMap = profitEntityMapper.toProfitMap(userProfits);
        List<PlayerJpaEntity> playerJpaEntities = getPlayers(playerIds);
        return playerJpaEntities.stream().map(playerJpaEntity -> profitEntityMapper.toProfit(playerJpaEntity, gameNumber, profitMap)).collect(Collectors.toList());
    }

    private List<Object[]> getUserProfit(LocalDate from, LocalDate to, List<PlayerId> playerIds) {
        Query query = entityManager.createNativeQuery(selectProfits.get(playerIds.isEmpty()))
                .setParameter(START_DATE_PARAM, from)
                .setParameter(END_DATE_PARAM, to);
        if (!playerIds.isEmpty()) {
            query.setParameter(PLAYER_IDS_PARAM, playerIds.stream().map(PlayerId::getId).collect(Collectors.toList()));
        }
        return query.getResultList();
    }

    private List<PlayerJpaEntity> getPlayers(List<PlayerId> playerIds) {
        Query query = entityManager.createQuery(selectPlayers.get(playerIds.isEmpty()));
        if (!playerIds.isEmpty()) {
            query.setParameter(PLAYER_IDS_PARAM, playerIds.stream().map(PlayerId::getId).collect(Collectors.toList()));
        }
        return query.getResultList();
    }

}
