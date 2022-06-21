package model.mode;

import model.card.Card;
import model.deck.Dobble;
import model.game.DobbleGame;

import java.util.ArrayList;
import java.util.List;

public class StackMode extends  Mode {


    public DobbleGame setZoneGame(DobbleGame dobbleGame) {

        Dobble dobble = dobbleGame.getDobble();

        Card firstCard  = dobble.getNthCard(0);
        Card secondCard = dobble.getNthCard(1);

        List<Card> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);

        dobbleGame.setCardsZone(cards);

        dobble.removeCard(firstCard);
        dobble.removeCard(secondCard);

        return dobbleGame;

    }



}
