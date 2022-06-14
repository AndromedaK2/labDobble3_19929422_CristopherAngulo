package model.game;

import model.card.Card;
import model.deck.Dobble;
import model.player.Player;

import java.util.List;

public interface IDobbleGame {

     String getWhoseIsTurn();


     List<Player> getPlayers();


     Dobble getDobble();


     GameStatus getStatus();


     List<Object> getTurns();


     int getPlayersNumber();


     void play();


     void register();
}
