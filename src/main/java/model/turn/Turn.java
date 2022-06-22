package model.turn;

import model.player.Player;

/** @author Cristopher Angulo
 * @implNote This class represent a turn in the game
 */
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
        return "\nJugador: " + player.getUsername().toUpperCase() +
                '\n';
    }
}

