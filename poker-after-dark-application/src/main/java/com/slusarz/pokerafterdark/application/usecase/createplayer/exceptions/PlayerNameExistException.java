package com.slusarz.pokerafterdark.application.usecase.createplayer.exceptions;

import com.slusarz.pokerafterdark.domain.player.Player;

public class PlayerNameExistException extends RuntimeException {
    public PlayerNameExistException(Player player) {
        super("Player name [" + player.getPlayerName() + "] exist.");
    }
}
