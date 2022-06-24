package model_19929422_CristopherAngulo.player;

import model_19929422_CristopherAngulo.card.Card;

import java.util.List;

/** @author Cristopher Angulo
 * @description player interface for the game
 */

public interface IPlayer {

    String getUsername();

    void addCards(List<Card> cards);

    int getPoints();

}
