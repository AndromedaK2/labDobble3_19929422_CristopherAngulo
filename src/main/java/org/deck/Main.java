package org.deck;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {



        List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(13);

        Dobble dobble = new Dobble(list,4,13);

        System.out.println(dobble.toString());



    }

}