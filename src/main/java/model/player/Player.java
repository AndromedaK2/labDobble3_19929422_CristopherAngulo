package model.player;

import model.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player implements IPlayer {
    private String username;

    private List<Card> cards = new ArrayList<>() ;
    private int points = cards.size();

    public String getUsername() {
        return username;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Player(String username) {
        this.username = username;
    }

    public List<Card> getCards() {
        return cards;
    }


    public void addCards(List<Card> cards){
        this.cards.addAll(cards);
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Jugador: \n" +
                "Nombre de usuario='" + username + '\'' +
                ",\n" + cards +
                ", points=" + points ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return points == player.points && Objects.equals(username, player.username) && Objects.equals(cards, player.cards);
    }



}
