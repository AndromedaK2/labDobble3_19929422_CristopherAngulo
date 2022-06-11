package org.deck;

import java.util.*;

public class Dobble {


    public Dobble(List<Object> elements, int elementsPerCard, int maximumTotalCards) {


        int order = getOrder(elementsPerCard);

        addCard(CreateFirstCard(elements,order));
        addCards(CreateNCards(elements,order));
        addCards(CreateNSquareCards(elements,order));


    }


    public List<Card> dobbleCards = new ArrayList<>();

    public void isDobble() {
        // TODO implement here
    }

    public void GetMissingCards() {
        // TODO implement here
    }

    public Card GetNthCard(List<Card> cards, int position) {
       return cards.get(position);
    }

    public void GetRequiredElements() {

    }

    public void GetTotalCards() {
        // TODO implement here
    }

    public void addCards(List<Card> cards){
        for (int i = 0; i < cards.size(); i++) {
            dobbleCards.add(GetNthCard(cards,i));
        }
    }

    public void addCard(Card card){
        dobbleCards.add(card);
    }

    private Card CreateFirstCard(List<Object> elements,Integer order) {
        Card card = new Card();
        for (int i=0; i<order+1;i++){
            Object element = elements.get(i);
            card.addElement(element);
        }
        return card;
    }

    private List<Card> CreateNCards(List<Object> elements,int order) {
        List<Card> cards = new ArrayList<>();
        for (int j=1; j<=order; j++) {
            Object firstElement  =  elements.get(0);
            Card card = new Card();
            card.addElement(firstElement);

            for (int k=1; k<=order; k++) {
                Object element = elements.get(calculateIndexToGetNCards(order,j,k));
                card.addElement(element);
            }
            cards.add(card);
        }
        return  cards;
    }

    private List<Card> CreateNSquareCards(List<Object> elements,int order) {
        List<Card> cards = new ArrayList<>();
        for (int i= 1; i<=order; i++) {
            for (int j=1; j<=order; j++) {
                Card card = new Card();
                card.addElement(elements.get(i));
                for (int k=1; k<= order; k++) {
                    Object element = elements.get(calculateIndexToGetNSquareCards(i,order,j,k));
                    card.addElement(element);
                }
                cards.add(card);
            }
        }
        return  cards;
    }

    private int calculateIndexToGetNCards( int order, int j, int k){
        return (order * j + (k+1))-1;
    }

    private int calculateIndexToGetNSquareCards(int i, int order, int j, int k){
       return (order+2+order*(k-1)+(((i-1)*(k-1)+j-1) % order))-1;
    }

    private int getOrder(int elementPerCard){
        return elementPerCard -1;
    }

    private void Shuffle() {

    }


    @Override
    public String toString() {
        String dobbleCardsString = "Mazo de cartas: \n";

        for (int i = 0; i < dobbleCards.size(); i++) {
            Card card = dobbleCards.get(i);
            String cardString = card.toString();
            dobbleCardsString = dobbleCardsString.concat(cardString);

        }
        return dobbleCardsString;


    }

}