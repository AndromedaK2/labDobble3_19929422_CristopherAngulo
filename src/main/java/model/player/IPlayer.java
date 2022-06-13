package model.player;

import model.deck.Dobble;

public interface IPlayer {

    String getUsername();
    Dobble getDobble();

    int getPoints();

}
