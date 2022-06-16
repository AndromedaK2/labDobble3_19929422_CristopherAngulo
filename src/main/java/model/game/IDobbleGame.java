package model.game;

import model.deck.Dobble;
import model.player.Player;

import java.util.List;

public interface IDobbleGame {

     String getWhoseIsTurn();


     List<Player> getPlayers();


     Dobble getDobble();


     DobbleGameStatus getStatus();


     List<Object> getTurns();


     int getPlayersNumber();


     void play();


     boolean register(String username);
}
