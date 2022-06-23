package model.mode;

import model.card.Card;
import model.game.DobbleGame;
import model.player.Player;

import java.util.List;

/** @author Cristopher Angulo
 * @description this class represent a concrete mode in game
 * @version 1.0 is in construction for future implementations
 */

public class EmptyHandsStack implements IMode {

    //region public methods
    @Override
    public DobbleGame startGame(DobbleGame dobbleGame) {
        /**
         List<Card> cards = new ArrayList<>();
         players.forEach(player -> player.setCards(cards));
         return players;**/
        return null;
    }

    @Override
    public boolean spotit(Object element, List<Card> cards) {
        return false;
    }

    @Override
    public Player updatePlayerCards(Player player,List<Card> cards) {
        return null;
    }


    @Override
    public List<Card> resetDobbleCards(List<Card> dobbleCards, List<Card> cards) {
        return null;
    }

    @Override
    public List<Card> resetCardsZone(List<Card> cards) {
        return null;
    }

    @Override
    public void endGame(DobbleGame dobbleGame) {}

    @Override
    public Player getWinner(List<Player> players) {
        return null;
    }
    //endregion




}
