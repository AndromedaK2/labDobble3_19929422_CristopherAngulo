package model.game;

import model.deck.Dobble;
import model.player.Player;

import java.util.List;

public class DobbleGame implements  IDobbleGame{

    public GameStatus gameStatus = GameStatus.CREATED;



    public String getWhoseIsTurn() {
        return null;
    }


    public List<Player> getPlayers() {
        return null;
    }


    public Dobble getDobble() {
        return null;
    }


    public GameStatus getStatus() {
        return null;
    }


    public List<Object> getTurns() {
        return null;
    }


    public int getPlayersNumber() {
        return 0;
    }


    public void play() {

    }


    public void register() {

    }
}
