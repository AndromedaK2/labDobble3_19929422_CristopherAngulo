package model.mode;

import model.card.Card;
import model.deck.Dobble;
import model.game.DobbleGame;
import model.player.Player;

import java.util.List;

public interface IMode {

    DobbleGame startGame(DobbleGame dobbleGame);
    boolean spotit(Object element,List<Card> cards);
    Player updatePlayerCards(Player player,List<Card> cards);

    List<Card> resetDobbleCards(List<Card> dobbleCards, List<Card> cards );

    List<Card> resetCardsZone(List<Card> cards );

    Player endGame(List<Player> players);

}
