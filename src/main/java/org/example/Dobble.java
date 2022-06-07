package org.example;

import java.util.*;

/**
 * 
 */
public class Dobble {

    /**
     * Default constructor
     */
    public Dobble() {
    }

    /**
     * 
     */
    public List<Card> DobbleCards;

    /**
     * 
     */
    public void isDobble() {
        // TODO implement here
    }

    /**
     * 
     */
    public void GetMissingCards() {
        // TODO implement here
    }

    /**
     * 
     */
    public void GetNthCard() {
        // TODO implement here
    }

    /**
     * 
     */
    public void GetRequiredCards() {
        // TODO implement here
    }

    /**
     * 
     */
    public void GetTotalCards() {
        // TODO implement here
    }

    /**
     * 
     */
    public void ToString() {
        // TODO implement here
    }

    /**
     * 
     */
    public Card CreateFirstCard(List<String> elements,Integer order) {
        Card card = new Card();
        for (Integer i=0; i<order;i++){
            String element = elements.get(i);
            card.addElement(element);
        }
        return card;
    }

    /**
     * 
     */
    public void CreateNCards(List<String> elements,Integer order) {
        Card card = new Card();

    }

    /**
     * 
     */
    public void CreateNSquareCards() {
        // TODO implement here
    }

    /**
     * 
     */
    public void Shuffle() {
        // TODO implement here
    }

}