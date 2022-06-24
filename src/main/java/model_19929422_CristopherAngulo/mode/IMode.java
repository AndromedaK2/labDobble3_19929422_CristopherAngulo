package model_19929422_CristopherAngulo.mode;

import model_19929422_CristopherAngulo.card.Card;
import model_19929422_CristopherAngulo.game.DobbleGame;
import model_19929422_CristopherAngulo.player.Player;

import java.util.List;

/** @author Cristopher Angulo
 * @description interface that represent contract for all game modes in dobbleGame
 */

public interface IMode {
    DobbleGame startGame(DobbleGame dobbleGame);
    boolean spotit(Object element,List<Card> cards);
    Player updatePlayerCards(Player player,List<Card> cards);
    List<Card> resetDobbleCards(List<Card> dobbleCards, List<Card> cards );
    List<Card> resetCardsZone(List<Card> cards );
    void endGame(DobbleGame dobbleGame);
    Player getWinner(List<Player> players);


}
