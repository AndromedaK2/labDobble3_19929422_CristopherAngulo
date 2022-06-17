package model.card;

import common.Helper;

import java.util.*;


public class Card implements ICard {
    private int id;
    private List<Object> elements;
    public List<Object> getElements() {
        return elements;
    }
    public int getId() {
        return id;
    }

    public Card() {
        id = Helper.generateRandomNumber(1,1000);
        elements = new ArrayList<>();
    }

    public void addElement(Object element) {
        if(!elements.contains(element))
        elements.add(element);
    }

    public Object getFirstElement() {
       return elements.get(0);
    }


    public Object getLastElement() {
        return elements.get(elements.size()-1);
    }

    public Boolean compareUniqueElements(Card FirstCard) {
        return null;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return id == card.id && Objects.equals(elements, card.elements);
    }


}