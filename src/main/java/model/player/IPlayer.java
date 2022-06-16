package model.player;

import model.card.Card;


import java.util.List;

public interface IPlayer {

    String getUsername();
    List<Card> getCards();

    int getPoints();

}
