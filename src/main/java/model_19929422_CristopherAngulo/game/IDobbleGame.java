package model_19929422_CristopherAngulo.game;

import model_19929422_CristopherAngulo.card.Card;
import model_19929422_CristopherAngulo.deck.Dobble;
import model_19929422_CristopherAngulo.mode.DobbleGameMode;
import model_19929422_CristopherAngulo.player.Player;
import model_19929422_CristopherAngulo.turn.Turn;

import java.util.List;

/** @author Cristopher Angulo
 * @description interface of dobble game
 */
 interface IDobbleGame {

     String getName();
     void setCardsZone(List<Card> cardsZone);
     void setMode(DobbleGameMode dobbleGameMode);
     Turn getWhoseIsTurn();
     List<Player> getPlayers();
     Dobble getDobble();
     DobbleGameStatus getStatus();
     List<Turn> getTurns();
     int getPlayersNumber();
     String register(String username);
     void startGame();
     boolean spotit(Object element);
     void passTurn();
     void nextTurn();
     void resetDobbleCards();
     void resetCardsZone();
     String getWinner();
     void endGame();

}
