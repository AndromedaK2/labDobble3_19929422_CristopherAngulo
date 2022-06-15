package model.game;

import model.deck.Dobble;
import model.player.Player;

import java.util.List;

public class DobbleGame implements  IDobbleGame{

    public GameStatus gameStatus = GameStatus.CREATED;

    public List<Player> Players;
    public Dobble dobbleCards;

    //public List<Turn> Turns;

    public int PlayersNumber;

    //public IMode Mode;

    public  DobbleGame (List<Object> elements,int elementsPerCard, int maximumTotalCards, String mode, int playersNumber){
        playersNumber = playersNumber;
        dobbleCards = new Dobble(elements,elementsPerCard,maximumTotalCards);

    }



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
