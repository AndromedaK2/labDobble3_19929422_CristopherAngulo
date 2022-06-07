package org.example;

import java.util.*;


public class Card {

    private String Id;
    private List<Object> elements;

    public List<Object> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        elements = elements;
    }


    public Card(String id, List<String> elements) {
        Id = id;
        elements = elements;
    }

    public Card() {
        Id = "1";
        elements = new ArrayList<>();
    }

    public void addElement(String element) {
        if(!elements.contains(element))
        elements.add(element);
    }

    public void addElement(Integer element) {
        if(!elements.contains(element))
            elements.add(element);
    }

    public Element GetFirstElement() {
        return null;
    }


    public Element GetLastElement() {
        return null;
    }


    public Boolean CompareUniqueElements(Card FirstCard) {
        return null;
    }

    public String ToString() {
        return "";
    }

}