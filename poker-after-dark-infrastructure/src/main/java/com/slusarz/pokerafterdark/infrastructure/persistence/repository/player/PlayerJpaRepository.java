package com.slusarz.pokerafterdark.infrastructure.persistence.repository.player;

import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.player.PlayerRepository;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.player.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class PlayerJpaRepository implements PlayerRepository {

    private static final String SELECT_PLAYERS_BY_IDS = "select p from PlayerJpaEntity p where p.id in ( :playersIds ) order by p.numberOfPlays desc";
    private static final String PLAYERS_IDS_PARAM = "playersIds";
    private static final String COUNT_PLAYERS_BY_NAME = "select count(p) from PlayerJpaEntity p where p.name = :playerName";
    private static final String PLAYER_NAME_PARAM = "playerName";

    @PersistenceContext
    private EntityManager entityManager;

    private PlayerEntityMapper playerEntityMapper;

    public PlayerJpaRepository(PlayerEntityMapper playerEntityMapper) {
        this.playerEntityMapper = playerEntityMapper;
    }

    @Override
    public List<Player> readPlayers(Set<PlayerId> playerIds) {
        List<PlayerJpaEntity> playerJpaEntities = entityManager.createQuery(SELECT_PLAYERS_BY_IDS)
                .setParameter(PLAYERS_IDS_PARAM, playerIds.stream().map(PlayerId::getId).collect(Collectors.toList()))
                .getResultList();
        return playerJpaEntities.stream().map(playerEntityMapper::toPlayer).collect(Collectors.toList());
    }

    @Override
    public boolean playerExist(PlayerName playerName) {
        return Integer.valueOf(entityManager.createQuery(COUNT_PLAYERS_BY_NAME)
                .setParameter(PLAYER_NAME_PARAM, playerName.getName())
                .getSingleResult().toString()) > 0;
    }

    @Override
    public PlayerId generateId() {
        return PlayerId.of(UUID.randomUUID().toString());
    }

    @Override
    public void save(Player player) {
        PlayerJpaEntity playerJpaEntity = entityManager.find(PlayerJpaEntity.class, player.getPlayerId().getId());
        if (Objects.isNull(playerJpaEntity)) {
            playerJpaEntity = PlayerJpaEntity.builder()
                    .id(player.getPlayerId().getId())
                    .name(player.getPlayerName().getName())
                    .numberOfPlays(player.getNumberOfPlays().getValue())
                    .liveWinnings(player.getLiveEarnings().getValue())
                    .build();
            entityManager.persist(playerJpaEntity);
        } else {
            playerJpaEntity.setLiveWinnings(player.getLiveEarnings().getValue());
            playerJpaEntity.setNumberOfPlays(player.getNumberOfPlays().getValue());
            entityManager.persist(playerJpaEntity);
        }
    }

    @Override
    public void save(List<Player> player) {
        player.forEach(this::save);
    }

}
