package model.turn;

import model.player.Player;

public class Turn {

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public Turn(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Jugador:" + player.getUsername() +
                '\n';
    }
}

