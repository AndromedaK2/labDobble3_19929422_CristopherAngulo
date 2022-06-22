package model.deck;

import model.card.Card;

import java.util.List;

/** @author Cristopher Angulo
 * @description interface of a cards set o dobble
 */

public interface IDobble {


     boolean isDobble();

     List<Card> getMissingCards();

     String missingCards();


    Card getNthCard(List<Card> cards, int position);

    int getRequiredElements(Card card);

    int getTotalCards(Card card);

    void addCards(List<Card> cards);

    void addCard(Card card);

}
