package org.deck;

import java.util.*;

/**
 * 
 */
public class Dobble {


    public Dobble() {
    }

    public List<Card> DobbleCards;

    public void isDobble() {
        // TODO implement here
    }

    public void GetMissingCards() {
        // TODO implement here
    }


    public void GetNthCard() {
        // TODO implement here
    }

    public void GetRequiredElements() {
        // TODO implement here
    }

    public void GetTotalCards() {
        // TODO implement here
    }

    public void ToString() {
        // TODO implement here
    }

    public Card CreateFirstCard(List<Object> elements,Integer order) {
        Card card = new Card();
        for (int i=0; i<order;i++){
            Object element = elements.get(i);
            card.addElement(element);
        }
        return card;
    }


    public List<Card> CreateNCards(List<Object> elements,int order) {
        List<Card> cards = new ArrayList<>();
        for (int j=1; j<=order; j++) {
            Object firstElement  =  elements.get(0);
            Card card = new Card();
            card.addElement(firstElement);

            for (int k=1; k<=order; k++) {
                card.addElement(calculateIndexToGetElementNSquare(elements,order,j,k));
            }
            cards.add(card);
        }
        return  cards;
    }


    public void CreateNSquareCards() {

    }

    public Object calculateIndexToGetElementNSquare( List<Object> elements, int order,int j, int k){
        return elements.get(order * j + (k+1));
    }


    public void Shuffle() {

    }

}