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

    //region attributes

    /**
     * @description represent unique value to identify. id is generated by a random function
     * @see Helper
     */
    private int id;

    /**
     * @description game status is represented by an enum (CREATED, STARTED, FINISHED)
     * @see DobbleGameStatus
     */
    private DobbleGameStatus gameStatus = DobbleGameStatus.CREATED;

    /**
     * @description represent Players of game
     * @see Player
     */
    private List<Player> players = new ArrayList<>();

    /**
     * @description Represent cards set or deck
     * @see  Dobble
     */
    private Dobble dobbleCards;

    /**
     * @description Represent number of players
     */
    private int playersNumber;

    /**
     * @description Represent cards set or deck
     * @see  Dobble
     */
    private List<Card> cardsZone = new ArrayList<>();

    /**
     * @description  game mode is represented by an enum (STACKMODE-EMPTYHANDSMODE,ETC)
     * @see DobbleGameStatus
     */
    private DobbleGameMode dobbleGameMode ;

    /**
     * @description  Represent the turns of game
     * @see Turn
     */
    private List<Turn> turns = new ArrayList<>();

    /**
     * @description  Represent the abstraction of a game mode
     * @see IMode
     */
    private IMode mode;

    /**
     * @description  Represent name or nick of game
     * @see Turn
     */
    private String name;
    //endregion

    //region Getter and Setters

    /**
     * @implNote get name of game
     * @return name of game
     */
    public String getName() {
        return name;
    }
    /**
     * @implNote get first turn
     * @return a turn
     */
    public Turn getWhoseIsTurn() {
        return this.turns.get(0);
    }

    /**
     * @implNote  get game players
     * @return players
     */

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @implNote get dobble
     * @return dobble
     */

    public Dobble getDobble() {
        return dobbleCards;
    }

    /**
     * @implNote  get status
     * @return dobble game status (FINISHED,STARTED,CREATED)
     */

    public DobbleGameStatus getStatus() {
        return gameStatus;
    }

    /**
     * @implNote  get all turns in the game
     * @return turns
     */

    public List<Turn> getTurns() {
        return turns;
    }

    /**
     * @implNote  get players number
     * @return players number
     *
     */

    public int getPlayersNumber() {
        return playersNumber;
    }

    /**
     * @implNote  get dobble game mode
     * @return dobble game mode
     */
    public DobbleGameMode getDobbleGameMode() {
        return dobbleGameMode;
    }



    /**
     * @implNote  get cards zone
     * @return cards
     */
    public List<Card> getCardsZone() {
        return cardsZone;
    }

    /**
     * @implNote  set game status
     */
    public void setGameStatus(DobbleGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
    /**
     * @implNote  set cards zone
     */
    public void setCardsZone(List<Card> cardsZone) {
        this.cardsZone = cardsZone;
    }
    /**
     * @implNote  set mode
     */
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
    //endregion

    //region constructor

    /**
     * Default Constructor
     */
    public DobbleGame(){

    }

    /**
     * @param elements
     * @param elementsPerCard
     * @param maximumTotalCards
     * @param dobbleGameMode
     * @param playersNumber
     * @param name
     * @param players
     * @param turns
     */

    public  DobbleGame (List<Object> elements, int elementsPerCard, int maximumTotalCards, DobbleGameMode dobbleGameMode, int playersNumber, String name, List<Player> players, List<Turn> turns){
        this.id  = Helper.generateRandomNumber(1,1000);
        this.playersNumber = playersNumber;
        this.dobbleCards = new Dobble(elements,elementsPerCard,maximumTotalCards);
        this.dobbleGameMode = dobbleGameMode;
        this.name = name;
        this.players = players;
        this.turns = turns;
        this.setMode(dobbleGameMode);
    }

    /**
     * @param elements
     * @param elementsPerCard
     * @param maximumTotalCards
     * @param dobbleGameMode
     * @param playersNumber
     * @param name
     */
    public  DobbleGame (List<Object> elements, int elementsPerCard, int maximumTotalCards, DobbleGameMode dobbleGameMode, int playersNumber, String name){
        this.id  = Helper.generateRandomNumber(1,1000);
        this.playersNumber = playersNumber;
        this.dobbleCards = new Dobble(elements,elementsPerCard,maximumTotalCards);
        this.dobbleGameMode = dobbleGameMode;
        this.name = name;
        this.setMode(dobbleGameMode);
    }
    // endregion

    // region public methods

    /**
     * @implNote this method register player with his username in the game
     * It also set up his turn.
     * @param username
     * @return true if method could register or false the opposite
     */
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

    /**
     * @implNote this method use mode to call start game function
     * @see IMode
     */

    public void startGame(){
        this.mode.startGame(this);
    }

    /**
     * @implNote this method use mode to call spotit.It also
     * updates player card, reset cards zone, reset dobble cards and continue with next turn
     * @see IMode
     * @return true if player fin common element between cards or false if it is the opposite
     */
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

    /**
     * @implNote this method execute pass turn when player skip his turn.
     * it also updates turns and reset cards
     */
    public void passTurn(){
        this.resetDobbleCards();
        this.resetCardsZone();
        Turn currentTurn = getWhoseIsTurn();
        int turnIndex = this.turns.indexOf(currentTurn);
        this.turns.add(this.turns.remove(turnIndex));
    }

    /**
     * @implNote  this method continue with next turn
     */
    public void nextTurn(){
        Turn currentTurn = getWhoseIsTurn();
        int turnIndex = this.turns.indexOf(currentTurn);
        this.turns.add(this.turns.remove(turnIndex));
    }

    /**
     * @implNote this method reset dobble cards using mode
     */
    public void resetDobbleCards(){
        this.dobbleCards.setDobbleCards(this.mode.resetDobbleCards(this.dobbleCards.getDobbleCards(), this.cardsZone));
    }

    /**
     * @implNote  this method reset cards zone using mode
     */
    public  void resetCardsZone(){
        this.cardsZone   = this.mode.resetCardsZone(this.cardsZone);
    }

    /**
     * @implNote  this methods get player who win the match
     * @return player string format
     */

    public String getWinner(){
       Player player = this.mode.getWinner(this.players);
       if(player.getPoints()>0){
           return player.toString();
       }else{
           return "No hay ganador";
       }
    }

    /**
     * @implNote  this method finish the game
     */
    public void endGame(){
        this.mode.endGame(this);
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
                "- zona de juego:" +  cardsZone +  "\n"+
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
    // endregion

}
