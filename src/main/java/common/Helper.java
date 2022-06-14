package common;

import java.util.ArrayList;
import java.util.List;

public class Helper {



    public static int generateRandomNumber(int min, int max){
        return (int)(Math.random()*(max-min+1)+min);
    }

    public static List<Object> generateRandomElements(int total){

        List<Object> elements = new ArrayList<>();


        List<Object> baseElements = new ArrayList<>();
        baseElements.add("Bear/s");
        baseElements.add("Tiger/s");
        baseElements.add("Wolf/s");
        baseElements.add("Bee/s");
        baseElements.add("Car/s");
        baseElements.add("Dog/s");
        baseElements.add("Snake/s");
        baseElements.add("Python/s");
        baseElements.add("Lion/s");
        baseElements.add("Spider/s");


        List<Object> positions = new ArrayList<>();
        positions.add("with");
        positions.add("on");
        positions.add("in");
        positions.add("beside");
        positions.add("above");
        positions.add("in front of");
        positions.add("under");
        positions.add("at");
        positions.add("behind");
        positions.add("next to");


        List<Object> objects = new ArrayList<>();
        objects.add("Computer");
        objects.add("Glasses");
        objects.add("Book");
        objects.add("Ball");
        objects.add("Guitar");
        objects.add("Sun");
        objects.add("Cinema");
        objects.add("Tv");
        objects.add("Window");
        objects.add("I");



        for (int i = 0; i < total ; i++) {
            Object baseElement = baseElements.get(generateRandomNumber(0,9));
            Object position = positions.get(generateRandomNumber(0,9));
            Object object = objects.get(generateRandomNumber(0,9));


            elements.add( generateRandomNumber(1,100) +" " + baseElement+" "+ position +" "+ object);



        }
        return elements;


    }


}
