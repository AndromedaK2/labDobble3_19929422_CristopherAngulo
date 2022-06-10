package org.deck;

import java.util.*;


public class Card {

    private String id;

    public List<Object> getElements() {
        return elements;
    }

    private List<Object> elements;


    public Card(String id, List<String> elements) {
        id = id;
        elements = elements;
    }

    public Card() {
        id = "1";
        elements = new ArrayList<>();
    }

    public void addElement(Object element) {
        if(!elements.contains(element))
        elements.add(element);
    }

    public Object GetFirstElement() {
        return null;
    }


    public Object GetLastElement() {
        return null;
    }


    public Boolean CompareUniqueElements(Card FirstCard) {
        return null;
    }

    public String ToString() {
        return "";
    }

}