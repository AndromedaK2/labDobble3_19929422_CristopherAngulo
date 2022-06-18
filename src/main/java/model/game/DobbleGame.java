package model.game;

import model.card.Card;
import model.deck.Dobble;
import model.mode.IMode;
import model.player.Player;
import model.turn.Turn;

import java.util.ArrayList;
import java.util.List;

public class DobbleGame implements  IDobbleGame{

    private String id;
    private DobbleGameStatus gameStatus = DobbleGameStatus.CREATED;
    private List<Player> players = new ArrayList<>();
    private Dobble dobbleCards;
    private int playersNumber;

    private List<Card> cardsZone;

    private List<Turn> turns;

    private IMode Mode;

    public  DobbleGame (List<Object> elements,int elementsPerCard, int maximumTotalCards, DobbleGameMode mode, int playersNumber){
        this.playersNumber = playersNumber;
        this.dobbleCards = new Dobble(elements,elementsPerCard,maximumTotalCards);
    }

    public String getWhoseIsTurn() {
        return this.turns.get(0).toString();
    }


    public List<Player> getPlayers() {
        return players;
    }


    public Dobble getDobble() {
        return dobbleCards;
    }


    public DobbleGameStatus getStatus() {
        return gameStatus;
    }


    public List<Object> getTurns() {
        return null;
    }


    public int getPlayersNumber() {
        return playersNumber;
    }


    public void play() {

    }


    public boolean register(String username) {
        Player player = new Player(username);
        if(!this.players.contains(player) && this.playersNumber >= this.players.size()  ){
            this.players.add(player);
            Turn turn = new Turn(player);
            this.turns.add(turn);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "DobbleGame{" +
                "id='" + id + '\'' +
                ", gameStatus=" + gameStatus +
                ", players=" + players +
                ", dobbleCards=" + dobbleCards +
                ", playersNumber=" + playersNumber +
                ", cardsZone=" + cardsZone +
                '}';
    }
}
