package model.card;

import common.Helper;


import java.util.*;

/** @author Cristopher Angulo
 * @implNote This class represent a card. Implement ICard
 * @see ICard
 */
public class Card implements ICard {
    //region attributes

    //endregion

    //region getter and setters

    //endregion

    //region constructor

    //endregion

    //region public methods

    //endregion


    //region private methods

    //endregion
    /**
     * @description id is generated by a random function
     * @see Helper
     */
    private int id;
    /**
     * @description list of any object (string, integer)
     */
    private List<Object> elements;

    /**
     * @implNote  get elements
     * @return list of elements
     */
    public List<Object> getElements() {
        return elements;
    }

    /**
     * @implNote  get id integer format
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @implNote main constructor
     */

    public Card() {
        id = Helper.generateRandomNumber(1,1000);
        elements = new ArrayList<>();
    }

    /**
     * @implNote add an element in the list of element
     * @param element
     */
    public void addElement(Object element) {
        if(!elements.contains(element))
        elements.add(element);
    }


    /**
     * @implNote  this method is overriding to return a card string
     * @return card in a string format
     */
    @Override
    public String toString() {
        String caption = "id: "+id+" Carta: [";
        for (int j = 0; j < elements.size(); j++) {
            Object element = elements.get(j);
            if(j+1 == elements.size()){
                caption = caption.concat(element+"]");
            }else{
                caption = caption.concat(element+"-");
            }
        }
        return caption+"\n";
    }

    /**
     * @implNote verify if 2 objects are equals accord properties and others validations
     * @param o any object
     * @return true if objects are equals or false if objects are not equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return id == card.id && Objects.equals(elements, card.elements);
    }


}