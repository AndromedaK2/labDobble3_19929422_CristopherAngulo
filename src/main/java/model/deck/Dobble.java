package model.deck;
import model.card.Card;

import java.util.*;

public class Dobble implements  IDobble {

    public int id = 0;
    public List<Card> dobbleCards = new ArrayList<>();

    public Dobble(){

    }
    public Dobble(List<Object> elements, int elementsPerCard, int maximumTotalCards) {
        int order = getOrder(elementsPerCard);
        addCard(createFirstCard(elements,order));
        addCards(createNCards(elements,order));
        addCards(createNSquareCards(elements,order));
        shuffle();
    }

    public void isDobble() {
        // TODO implement here
    }

    public void MissingCards() {

    }

    public Card getNthCard(List<Card> cards, int position) {
       return cards.get(position);
    }

    public int getRequiredElements(Card card) {
        int elementsPerCard = card.getElements().size();
        int order = getOrder(elementsPerCard);
        return (order * order)+ order + 1;
    }

    public void getTotalCards(Card card) {

    }


    public void addCards(List<Card> cards){
        for (int i = 0; i < cards.size(); i++) {
            dobbleCards.add(getNthCard(cards,i));
        }
    }

    public void addCard(Card card){
        dobbleCards.add(card);
    }

    private Card createFirstCard(List<Object> elements,Integer order) {
        Card card = new Card();
        for (int i=0; i<order+1;i++){
            Object element = elements.get(i);
            card.addElement(element);
        }
        return card;
    }

    private List<Card> createNCards(List<Object> elements,int order) {
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

    private List<Card> createNSquareCards(List<Object> elements,int order) {
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

    private void shuffle() {
        dobbleCards.sort(Comparator.comparingInt(Card::getId));
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dobble)) return false;
        Dobble dobble = (Dobble) o;
        return id == dobble.id && Objects.equals(dobbleCards, dobble.dobbleCards);
    }



}