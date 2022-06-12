package model.card;

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
        id = generateRandomNumber();
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
        String caption = "id: "+id+" Carta: ";
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

    private int generateRandomNumber(){
        int min = 1;
        int max = 1000;
        return (int)(Math.random()*(max-min+1)+min);
    }

}