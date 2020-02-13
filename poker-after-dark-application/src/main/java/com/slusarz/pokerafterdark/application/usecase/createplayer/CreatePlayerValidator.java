package com.slusarz.pokerafterdark.application.usecase.createplayer;

import com.slusarz.pokerafterdark.application.usecase.createplayer.exceptions.PlayerNameExistException;
import com.slusarz.pokerafterdark.domain.player.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePlayerValidator {

    private PlayerRepository playerRepository;

    public void validate(Player player) {
        boolean exist = playerRepository.playerExist(player.getPlayerName());
        if (exist){
            throw new PlayerNameExistException(player);
        }
    }

}
