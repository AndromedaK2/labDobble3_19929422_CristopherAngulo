package model.mode;

import model.card.Card;
import model.deck.Dobble;
import model.player.Player;

import java.util.List;

public interface IMode {

    /***
        Repartir cartas a los jugadores    **/
    List<Player> dealCards(List<Player> players, Dobble dobbleCards);
    void setZoneGame();
    void spotit();
    void pass();



}
