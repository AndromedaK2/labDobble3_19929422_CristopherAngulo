package model.deck;

import java.util.*;


public class Card implements ICard {
    private int i = 0;
    private String id;

    public List<Object> getElements() {
        return elements;
    }

    private List<Object> elements;


    public Card() {
        id = String.valueOf(1+i);
        elements = new ArrayList<>();
    }

    public void addElement(Object element) {
        if(!elements.contains(element))
        elements.add(element);
    }

    public Object getFirstElement() {
        return null;
    }


    public Object getLastElement() {
        return null;
    }

    public Boolean compareUniqueElements(Card FirstCard) {
        return null;
    }

    @Override
    public String toString() {
        String caption = "Carta: ";
        for (int j = 0; j < elements.size(); j++) {
            Object element = elements.get(j);
            if(j+1 == elements.size()){
                caption = caption.concat(element+"");
            }else{
                caption = caption.concat(element+"-");
            }
        }
        return caption+"\n";
    }

}