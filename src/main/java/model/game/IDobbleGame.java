package model.game;

import model.deck.Dobble;
import model.player.Player;
import model.turn.Turn;

import java.util.List;

public interface IDobbleGame {

     Turn getWhoseIsTurn();


     List<Player> getPlayers();


     Dobble getDobble();


     DobbleGameStatus getStatus();


     List<Object> getTurns();


     int getPlayersNumber();




     boolean register(String username);
}
