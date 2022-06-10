package org.deck;

import java.util.*;

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
        for (int i=0; i<order+1;i++){
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
                card.addElement(calculateIndexToGetNCards(elements,order,j,k));
            }
            cards.add(card);
        }
        return  cards;
    }


    public List<Card> CreateNSquareCards(List<Object> elements,int order) {
        List<Card> cards = new ArrayList<>();
        for (int i= 1; i<=order; i++) {
            for (int j=1; j<=order; j++) {
                Card card = new Card();
                card.addElement(elements.get(i+1));
                for (int k=1; k<= order; k++) {
                    card.addElement(calculateIndexToGetNSquareCards(elements,i,order-1,j,k));
                }
                cards.add(card);
            }
        }
        return  cards;
    }

    public Object calculateIndexToGetNCards( List<Object> elements, int order, int j, int k){
        return elements.get(((order-1) * j + (k+1))-1);
    }

    public Object calculateIndexToGetNSquareCards( List<Object> elements,int i, int order, int j, int k){
        return elements.get((order+2+order*(k-1)+(((i-1)*(k-1)+j-1) % order))-1);
    }



    public void Shuffle() {

    }

}