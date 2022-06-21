package model.player;

import model.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player implements IPlayer {
    private String username;

    private List<Card> cards = new ArrayList<>() ;
    private int points = 0;

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
        calculatePoints();
        return "Jugador: \n" +
                "Nombre de usuario='" + username + '\'' +
                ",\n" + cards.toString() +
                ", points=" + points ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return points == player.points && Objects.equals(username, player.username) && Objects.equals(cards, player.cards);
    }

    private void calculatePoints(){
        for (int i = 0; i < cards.size(); i++) {
            points = points + 1;
        }
    }


}
