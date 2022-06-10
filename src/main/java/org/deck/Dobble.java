package org.deck;

import java.util.*;

public class Dobble {


    public Dobble() {
        List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);

        addCard(CreateFirstCard(list,3));
        addCards(CreateNCards(list,3));
        addCards(CreateNSquareCards(list,3));


        for (int i = 0; i < DobbleCards.size(); i++) {
          System.out.println( DobbleCards.get(i).getElements());
        }


    }


    public List<Card> DobbleCards = new ArrayList<>();

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
        // TODO implement here
    }

    public void GetTotalCards() {
        // TODO implement here
    }

    public void ToString() {


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
        return elements.get((order-1) * j + (k+1));
    }

    public Object calculateIndexToGetNSquareCards( List<Object> elements,int i, int order, int j, int k){
        return elements.get((order+2+order*(k-1)+(((i-1)*(k-1)+j-1) % order))-1);
    }

    public void addCards(List<Card> cards){
        for (int i = 0; i < cards.size(); i++) {
            DobbleCards.add(GetNthCard(cards,i));
        }
    }

    public void addCard(Card card){
        DobbleCards.add(card);
    }


    public void Shuffle() {

    }

}