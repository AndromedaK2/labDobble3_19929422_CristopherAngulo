package model.game;

import model.card.Card;
import model.deck.Dobble;
import model.player.Player;
import model.turn.Turn;

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
     boolean register(String username);
     void startGame();
     boolean spotit(Object element);
     void passTurn();
     void nextTurn();
     void resetDobbleCards();
     void resetCardsZone();
     String getWinner();
     void endGame();

}
