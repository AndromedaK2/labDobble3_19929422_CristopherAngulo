package model.game;

import common.Helper;
import model.card.Card;
import model.deck.Dobble;
import model.mode.EmptyHandsStack;
import model.mode.IMode;
import model.mode.StackMode;
import model.player.Player;
import model.turn.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DobbleGame implements  IDobbleGame{

    private int id;
    private DobbleGameStatus gameStatus = DobbleGameStatus.CREATED;
    private List<Player> players = new ArrayList<>();
    private Dobble dobbleCards;
    private int playersNumber;
    private List<Card> cardsZone ;
    private DobbleGameMode dobbleGameMode ;
    private List<Turn> turns = new ArrayList<>();

    private IMode mode;

    private String name;

    public String getName() {
        return name;
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


    public List<Turn> getTurns() {
        return turns;
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

    public  DobbleGame (List<Object> elements,int elementsPerCard, int maximumTotalCards, DobbleGameMode dobbleGameMode, int playersNumber,String name){
        this.id  = Helper.generateRandomNumber(1,1000);
        this.playersNumber = playersNumber;
        this.dobbleCards = new Dobble(elements,elementsPerCard,maximumTotalCards);
        this.dobbleGameMode = dobbleGameMode;
        this.name = name;
        this.setMode(dobbleGameMode);
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
            this.nextTurn();
            return true;

        }
        this.resetDobbleCards();
        this.resetCardsZone();
        this.nextTurn();
        return false;

    }

    public void passTurn(){
        this.resetDobbleCards();
        this.resetCardsZone();
        Turn currentTurn = getWhoseIsTurn();
        int turnIndex = this.turns.indexOf(currentTurn);
        this.turns.add(this.turns.remove(turnIndex));
    }

    public void nextTurn(){
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

    public Player endGame(){
        this.gameStatus = DobbleGameStatus.FINISHED;
        return this.mode.endGame(this.players);
    }

    /**
     * @implNote  this method is overriding to return a dobble game string
     * @return dobble game in a string format
     */
    @Override
    public String toString() {
        return "\nInformacion del Juego Dobble:\n" +
                "- id: " + id + "\n" +
                "- nombre: " + name + "\n" +
                "- estado: " + gameStatus +  "\n" +
                "- jugadores: " + players +  "\n" +
                "- numero de jugadores:" + playersNumber +  "\n" +
                "- zona de juego:" + cardsZone +  "\n"+
                "- " + dobbleCards +  "\n" ;

    }

    /**
     * @implNote verify if 2 objects are equals accord properties and others validations
     * @param o any object
     * @return true if objects are equals or false if objects are not equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DobbleGame)) return false;
        DobbleGame that = (DobbleGame) o;
        return id == that.id && playersNumber == that.playersNumber
                && gameStatus == that.gameStatus
                && Objects.equals(players, that.players)
                && Objects.equals(dobbleCards, that.dobbleCards)
                && Objects.equals(cardsZone, that.cardsZone)
                && dobbleGameMode == that.dobbleGameMode
                && Objects.equals(turns, that.turns)
                && Objects.equals(mode, that.mode)
                && Objects.equals(name, that.name);
    }

}
