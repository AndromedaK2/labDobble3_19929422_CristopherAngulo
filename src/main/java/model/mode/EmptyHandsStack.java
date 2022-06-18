package model.mode;

import model.card.Card;
import model.deck.Dobble;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class EmptyHandsStack extends Mode {

    public List<Player> dealCards(List<Player> players, Dobble dobbleCards) {
        List<Card> cards = new ArrayList<>();
        players.forEach(player -> player.setCards(cards));
        return players;
    }


}
