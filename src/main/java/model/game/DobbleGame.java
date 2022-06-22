package model.game;

import model.card.Card;
import model.deck.Dobble;
import model.mode.EmptyHandsStack;
import model.mode.IMode;
import model.mode.StackMode;
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
    private List<Card> cardsZone ;
    private DobbleGameMode dobbleGameMode ;
    private List<Turn> turns = new ArrayList<>();

    private IMode mode;
    public IMode getMode() {
        return mode;
    }

    public Turn getWhoseIsTurn() {
        return this.turns.get(0);
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


    public void setGameStatus(DobbleGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Card> getCardsZone() {
        return cardsZone;
    }

    public void setCardsZone(List<Card> cardsZone) {
        this.cardsZone = cardsZone;
    }





    public  DobbleGame (List<Object> elements,int elementsPerCard, int maximumTotalCards, DobbleGameMode dobbleGameMode, int playersNumber){
        this.playersNumber = playersNumber;
        this.dobbleCards = new Dobble(elements,elementsPerCard,maximumTotalCards);
        this.dobbleGameMode = dobbleGameMode;
        this.setMode(dobbleGameMode);
    }



    public void setMode(DobbleGameMode dobbleGameMode) {
        switch (dobbleGameMode){
            case STACKMODE:
                this.mode = new StackMode();
                break;
            case EMPTYHANDSMODE:
                this.mode = new EmptyHandsStack();
                break;
        }
    }

    public boolean register(String username) {
        Player player = new Player(username);
        if(!this.players.contains(player) && this.playersNumber > this.players.size()){
            this.players.add(player);
            Turn turn = new Turn(player);
            this.turns.add(turn);
            return true;
        }
        return false;
    }

    public void startGame(){
        this.mode.startGame(this);
    }
    public boolean spotit(Object element){
        boolean spotit  = this.mode.spotit(element,this.cardsZone);
        if(spotit){
            Player player  = getWhoseIsTurn().getPlayer();
            int playerIndex = this.players.indexOf(player);
            player = this.mode.updatePlayerCards(player,this.cardsZone);
            this.players.set(playerIndex,player);
            this.resetCardsZone();
            this.passTurn();
            return true;

        }
        this.resetDobbleCards();
        this.resetCardsZone();
        this.passTurn();
        return false;

    }

    public void passTurn(){
        Turn currentTurn = getWhoseIsTurn();
        int turnIndex = this.turns.indexOf(currentTurn);
        this.turns.add(this.turns.remove(turnIndex));
    }

    public void resetDobbleCards(){
        this.dobbleCards.setDobbleCards(this.mode.resetDobbleCards(this.dobbleCards.getDobbleCards(), this.cardsZone));
    }

    public  void resetCardsZone(){
        this.cardsZone   = this.mode.resetCardsZone(this.cardsZone);
    }



    public void endGame(){
        this.gameStatus = DobbleGameStatus.FINISHED;
        Player winner =  this.mode.endGame(this.players);
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
