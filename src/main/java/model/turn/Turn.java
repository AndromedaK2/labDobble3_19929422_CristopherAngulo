package model.turn;

import model.player.Player;

/** @author Cristopher Angulo
 * @implNote This class represent a turn in the game
 * It is possible to keep extend this class with
 * @see Player
 */
public class Turn {

    //region attributes
    /**
     * @description Player associated to the current turn
     */
    private Player player;
    //endregion

    //region getter and setters
    public Player getPlayer() {
        return player;
    }

    //endregion

    //region constructor
    public Turn(Player player) {
        this.player = player;
    }
    //endregion

    //region public methods

    /**
     * @implNote  this method is overriding to return a turn string
     * @return turn in a string format
     */

    @Override
    public String toString() {
        return "\nJugador: " + player.getUsername().toUpperCase() +
                '\n';
    }
    //endregion


}

