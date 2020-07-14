package com.slusarz.pokerafterdark.application.usecase.createplayer.validator;

import com.slusarz.pokerafterdark.application.usecase.createplayer.exceptions.PlayerNameExistException;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.player.PlayerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePlayerValidator {

    private PlayerRepository playerRepository;

    public void validate(PlayerName playerName) {
        boolean exist = playerRepository.playerExist(playerName);
        if (exist){
            throw new PlayerNameExistException(playerName);
        }
    }

}
