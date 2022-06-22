package model.deck;
import common.Helper;
import model.card.Card;

import java.util.*;
import java.util.stream.Collectors;


public class Dobble implements  IDobble {

    private int id;
    private List<Card> dobbleCards = new ArrayList<>();
    private List<Object> allElements;
    private List<Card> missingCards = new ArrayList<>();
    private int order;

    public List<Card> getDobbleCards() {
        return dobbleCards;
    }

    public void setDobbleCards(List<Card> dobbleCards) {
        this.dobbleCards = dobbleCards;
    }

    private int elementsPerCard;

    /**
     *
     * @param elements  symbols or elements of cards
     * @param elementsPerCard quantity elements per card
     * @param maximumTotalCards maximum total cards of deck
     */
    public Dobble(List<Object> elements, int elementsPerCard, int maximumTotalCards) {
        this.id = Helper.generateRandomNumber(1,10000000);
        this.allElements  = elements;
        this.elementsPerCard = elementsPerCard;
        this.order = getOrder(elementsPerCard);
        addCard(createFirstCard(elements,order));
        addCards(createNCards(elements,order));
        addCards(createNSquareCards(elements,order));
        shuffle();
        if(!(getMaxNumberOfCards(order)==maximumTotalCards)){
            List<Card> dobbleCards =  new ArrayList<>();
            int i = 0;

            for (; i < maximumTotalCards ; i++) {
                dobbleCards.add(this.dobbleCards.get(i));
            }

            for (int j = i; j <this.dobbleCards.size();j++){
                this.missingCards.add(this.dobbleCards.get(j));
            }

            this.dobbleCards = dobbleCards;

        }

        isDobble();
    }

    public boolean isDobble() {
        boolean allCardsHaveDifferentElements        = this.allCardsHaveDifferentElements();
        boolean betweenAllCardsHaveDifferentElements = this.betweenAllCardsHaveOneElementInCommon();
        return (allCardsHaveDifferentElements && betweenAllCardsHaveDifferentElements)? true : false;
    }

    public String missingCards() {

        String missingCardsString = "\nCartas Faltantes: \n";
        for (int i = 0; i < missingCards.size(); i++) {
            Card card = missingCards.get(i);
            String cardString = card.toString();
            missingCardsString = missingCardsString.concat(cardString);
        }

        return ""+missingCardsString;

    }

    public List<Card> getMissingCards(){
        return missingCards;
    }

    public Card getNthCard(List<Card> cards, int position) {
        return cards.get(position);
    }

    public Card getNthCard(int position) {
        return this.dobbleCards.get(position);
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

    public void removeCard(Card card){
        this.dobbleCards.remove(card);
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

    private boolean allCardsHaveDifferentElements (){
      return this.dobbleCards.stream().allMatch(card -> card.getElements().stream().distinct().count() == this.elementsPerCard);
    }

    private boolean betweenAllCardsHaveOneElementInCommon(){
        int auxiliary = 0;
        for (int i = 0; i < this.dobbleCards.size() ; i++) {
            auxiliary = auxiliary+1;

            for (int j = auxiliary; j < this.dobbleCards.size(); j++) {
                Card firstCard =  this.dobbleCards.get(i);
                Card nextCard  =  this.dobbleCards.get(j);
                if( !(firstCard.getElements().stream().filter(nextCard.getElements()::contains).
                        collect(Collectors.toList()).size() == 1)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * @implNote  this method is overriding to return a dobble string
     * @return dobble in a string format
     */
    @Override
    public String toString() {
        String dobbleCardsString = "Mazo de cartas: \n";
        for (int i = 0; i < dobbleCards.size(); i++) {
            Card card = dobbleCards.get(i);
            String cardString = card.toString();
            dobbleCardsString = dobbleCardsString.concat(cardString);
        }


        return  dobbleCardsString;
    }

    /**
     * @implNote verify if 2 objects are equals accord properties and others validations
     * @param o any object
     * @return true if objects are equals or false if objects are not equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dobble)) return false;
        Dobble dobble = (Dobble) o;
        return id == dobble.id && order == dobble.order
                && elementsPerCard == dobble.elementsPerCard
                && Objects.equals(dobbleCards, dobble.dobbleCards)
                && Objects.equals(allElements, dobble.allElements)
                && Objects.equals(missingCards, dobble.missingCards);
    }


    public List<Object> getAllElements() {
        return allElements;
    }
}