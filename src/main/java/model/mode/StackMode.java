package model.mode;

import model.card.Card;
import model.deck.Dobble;
import model.game.DobbleGame;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class StackMode implements IMode {



    @Override
    public DobbleGame startGame(DobbleGame dobbleGame) {
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

    @Override
    public boolean spotit(Object element,List<Card> cards) {
        Card firstCard  = cards.get(0);
        Card secondCard = cards.get(1);
        return firstCard.getElements().contains(element) && secondCard.getElements().contains(element);
    }

    @Override
    public Player updatePlayerCards(Player player,List<Card> cards) {
        player.addCards(cards);
        return player;
    }

    @Override
    public DobbleGame passTurn(DobbleGame dobbleGame) {
        return null;
    }

    @Override
    public List<Card> resetDobbleCards(List<Card> dobbleCards, List<Card> cards) {
        dobbleCards.addAll(cards);
        return dobbleCards;
    }
    @Override
    public List<Card> resetCardsZone( List<Card> cards) {
        cards.removeAll(cards);
        return cards;
    }

    @Override
    public Player endGame(List<Player> players) {
        return null;
    }
}
