package model.player;

import model.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** @author Cristopher Angulo
 * @implNote This class represent a dobble game player. Implement IPlayer
 * @see IPlayer
 */

public class Player implements IPlayer {

    /**
     * @description Username of player in string format
     */
    private String username;
    /**
     * @description this list of cards owned by player
     */
    private List<Card> cards = new ArrayList<>() ;
    /**
     * @description Is the sum  for each card
     */
    private int points = cards.size();

     /**
     * @return player username
     */

    public String getUsername() {
        return username;
    }
    public int getPoints() { return points; }
    public Player(String username) {
        this.username = username;
    }
    public List<Card> getCards() {
        return cards;
    }
    public void addCards(List<Card> cards){
        this.cards.addAll(cards);
    }

    /**
     * @implNote  this method is overriding to return a player string
     * @return player in a string format
     */
    @Override
    public String toString() {
        return "Jugador: \n" +
                "Nombre de usuario='" + username + '\'' +
                ",\n" + cards +
                ", points=" + points ;
    }

    /**
     * @implNote verify if 2 objects are equals accord properties and others validations
     * @param o any object
     * @return true if objects are equals or false if objects are not equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return points == player.points && Objects.equals(username, player.username) && Objects.equals(cards, player.cards);
    }



}
