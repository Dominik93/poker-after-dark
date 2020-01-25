package com.slusarz.pokerafterdark.infrastructure.persistence.repository.player;

import com.slusarz.pokerafterdark.application.usecase.createplayer.PlayerRepository;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.infrastructure.persistence.entity.PlayerJpaEntity;
import com.slusarz.pokerafterdark.infrastructure.persistence.mapper.PlayerEntityMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class PlayerJpaRepository implements PlayerRepository {

    private static final String SELECT_PLAYERS_BY_IDS = "select p from PlayerJpaEntity p where p.id in ( :playersIds ) order by p.numberOfPlays desc";
    private static final String PLAYERS_IDS_PARAM = "playersIds";

    @PersistenceContext
    private EntityManager entityManager;

    private PlayerEntityMapper playerEntityMapper;

    public PlayerJpaRepository(PlayerEntityMapper playerEntityMapper) {
        this.playerEntityMapper = playerEntityMapper;
    }

    public List<Player> readPlayers(Set<PlayerId> playerIds) {
        List<PlayerJpaEntity> playerJpaEntities = entityManager.createQuery(SELECT_PLAYERS_BY_IDS)
                .setParameter(PLAYERS_IDS_PARAM, playerIds.stream().map(PlayerId::getId).collect(Collectors.toList()))
                .getResultList();
        return playerJpaEntities.stream().map(playerEntityMapper::toPlayer).collect(Collectors.toList());
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
