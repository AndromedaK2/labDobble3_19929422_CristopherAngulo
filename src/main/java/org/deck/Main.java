package org.deck;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Object> list = new ArrayList<Object>();
        list.add("1");
        list.add("2");
        list.add("8");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");


        Dobble dobble = new Dobble();
        Card  firstCard = dobble.CreateFirstCard(list,3);
        System.out.println(firstCard.getElements());

    }

}