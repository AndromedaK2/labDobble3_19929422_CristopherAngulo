package model.deck;

import model.card.Card;

import java.util.List;

public interface IDobble {


     void isDobble();

     void MissingCards();

     Card getNthCard(List<Card> cards, int position);

    void getRequiredElements(Card card);

     void getTotalCards(Card card);

    void addCards(List<Card> cards);

    void addCard(Card card);

}
