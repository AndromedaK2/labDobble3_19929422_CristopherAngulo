package model.deck;
import common.Helper;
import model.card.Card;

import java.util.*;


public class Dobble implements  IDobble {

    public int id = 0;
    public List<Card> dobbleCards = new ArrayList<>();


    /**
     *
     * @param elements
     * @param elementsPerCard
     * @param maximumTotalCards
     */
    public Dobble(List<Object> elements, int elementsPerCard, int maximumTotalCards) {
        this.id = Helper.generateRandomNumber(1,10000000);
        int order = getOrder(elementsPerCard);
        addCard(createFirstCard(elements,order));
        addCards(createNCards(elements,order));
        addCards(createNSquareCards(elements,order));
        shuffle();
        if(!(getMaxNumberOfCards(order)==maximumTotalCards)){
            List<Card> dobbleCards =  new ArrayList<>();
            for (int i = 0; i < maximumTotalCards ; i++) {
                dobbleCards.add(this.dobbleCards.get(i));
            }
            this.dobbleCards = dobbleCards;
        }
    }

    public boolean isDobble() {
        return true;
    }

    public List<Card> MissingCards() {
        return null;
    }

    public Card getNthCard(List<Card> cards, int position) {
       return cards.get(position);
    }

    /**
     *
     * @param card
     * @return required elements to create dobble
     */
    public int getRequiredElements(Card card) {
        int elementsPerCard = card.getElements().size();
        int order = getOrder(elementsPerCard);
        return (order * order)+ order + 1;
    }

    /**
     *
     * @param card
     * @return total cards to create dobble
     */
    public int getTotalCards(Card card) {
        int elementsPerCard = card.getElements().size();
        int order = getOrder(elementsPerCard);
        return (order * order)+ order + 1;
    }

    /**
     *
     * @param order
     * @return maximum number of cards
     */
    public int getMaxNumberOfCards(int order){
        return (order*order)+order+1;
    }

    /**
     *
     * @param cards
     */
    public void addCards(List<Card> cards){
        for (int i = 0; i < cards.size(); i++) {
            dobbleCards.add(getNthCard(cards,i));
        }
    }

    /**
     *
     * @param card
     */
    public void addCard(Card card){
        dobbleCards.add(card);
    }

    /**
     *
     * @param elements
     * @param order
     * @return The first card of dobble
     */
    private Card createFirstCard(List<Object> elements,Integer order) {
        Card card = new Card();
        for (int i=0; i<order+1;i++){
            Object element = elements.get(i);
            card.addElement(element);
        }
        return card;
    }

    /**
     *
     * @param elements
     * @param order
     * @see Card
     * @return card list accord to N
     */

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

    /**
     * @param elements
     * @param order
     * @see Card
     * @return card list accord to N square
     */
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

    /**
     * @param order
     * @param j
     * @param k
     * @return index to get a element in the creation of N cards.
     */
    private int calculateIndexToGetNCards( int order, int j, int k){
        return (order * j + (k+1))-1;
    }

    /**
     * @param i
     * @param order
     * @param j
     * @param k
     * @return
     */
    private int calculateIndexToGetNSquareCards(int i, int order, int j, int k){
       return (order+2+order*(k-1)+(((i-1)*(k-1)+j-1) % order))-1;
    }

    /**
     *
     * @param elementPerCard
     * @return
     */
    private int getOrder(int elementPerCard){
        return elementPerCard -1;
    }

    /**
     *
     */
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