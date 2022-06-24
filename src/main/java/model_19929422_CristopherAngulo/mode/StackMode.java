package model_19929422_CristopherAngulo.mode;

import model_19929422_CristopherAngulo.card.Card;
import model_19929422_CristopherAngulo.deck.Dobble;
import model_19929422_CristopherAngulo.game.DobbleGame;
import model_19929422_CristopherAngulo.player.Player;

import java.util.*;

/** @author Cristopher Angulo
 * @description this class represent a concrete mode in game
 */

public class StackMode implements IMode {


    //region public methods

    /**
     * @implNote initialize and start game with a specific logic. In this case we get the first card
     * and second card, then we add this cards in the zone cards and remove them of deck or cards set
     * @param dobbleGame representation of a game
     * @return  dobble game
     */
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

    /**
     * @implNote identify the element in common between 2 cards
     * @param element any object
     * @param cards list of cards
     * @return true if 2 cards have the element in common or false if it is the opposite
     */
    @Override
    public boolean spotit(Object element,List<Card> cards) {
        Card firstCard  = cards.get(0);
        Card secondCard = cards.get(1);
        return firstCard.getElements().contains(element) && secondCard.getElements().contains(element);
    }

    /**
     * @implNote update players cards using method addcards of player object
     * @param player represent a player in the game
     * @param cards represent list of cards
     * @return player with new cards
     */
    @Override
    public Player updatePlayerCards(Player player,List<Card> cards) {
        player.addCards(cards);
        return player;
    }

    /**
     * @implNote this method send zone cards to bottom of cards set
     * @param dobbleCards represents list of cards
     * @param cards represent zone cards
     * @return list of cards
     */

    @Override
    public List<Card> resetDobbleCards(List<Card> dobbleCards, List<Card> cards) {
        dobbleCards.addAll(cards);
        return dobbleCards;
    }

    /**
     * @implNote  this method clean cards zone
     * @param cards represent list of cards
     * @return clean cards zone
     */
    @Override
    public List<Card> resetCardsZone( List<Card> cards) {
        cards.removeAll(cards);
        return cards;
    }

    /**
     * @implNote reset dobble game
     */

    @Override
    public void endGame(DobbleGame dobbleGame) {
        dobbleGame.getPlayers().forEach(player->{
            player.removeCards();
        });
    }

    /**
     * @implNote  this method get the winner of the game comparing the points associated
     * @param players represent list of players
     * @return winner as a player
     */

    @Override
    public Player getWinner(List<Player> players) {
        Player player =  players.stream()
                .max(Comparator.comparing(Player::getPoints))
                .get();
        return player;
    }




    //endregion




}
