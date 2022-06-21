package model.mode;

import model.card.Card;
import model.deck.Dobble;
import model.game.DobbleGame;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class EmptyHandsStack implements IMode {


    @Override
    public DobbleGame startGame(DobbleGame dobbleGame) {
        /**
        List<Card> cards = new ArrayList<>();
        players.forEach(player -> player.setCards(cards));
        return players;**/
        return null;
    }

    @Override
    public boolean spotit(Object element, List<Card> cards) {
        return false;
    }

    @Override
    public Player updatePlayerCards(Player player,List<Card> cards) {
        return null;
    }

    @Override
    public DobbleGame passTurn(DobbleGame dobbleGame) {
        return null;
    }

    @Override
    public List<Card> resetDobbleCards(List<Card> dobbleCards, List<Card> cards) {
        return null;
    }

    @Override
    public List<Card> resetCardsZone(List<Card> cards) {
        return null;
    }

    @Override
    public Player endGame(List<Player> players) {
        return null;
    }
}
