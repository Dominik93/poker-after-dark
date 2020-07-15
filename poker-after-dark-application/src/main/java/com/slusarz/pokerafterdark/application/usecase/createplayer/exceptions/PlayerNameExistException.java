package com.slusarz.pokerafterdark.application.usecase.createplayer.exceptions;

import com.slusarz.pokerafterdark.domain.player.PlayerName;

public class PlayerNameExistException extends RuntimeException {
    public PlayerNameExistException(PlayerName playerName) {
        super("Player with name [" + playerName + "] exist.");
    }
}
