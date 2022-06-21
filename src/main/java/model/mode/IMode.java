package model.mode;

import model.card.Card;
import model.deck.Dobble;
import model.player.Player;

import java.util.List;

public interface IMode {


    boolean spotit(Object element, Card firstCard, Card secondCard);
    void pass();

    void finish();



}
