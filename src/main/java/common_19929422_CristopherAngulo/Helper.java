package common_19929422_CristopherAngulo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cristopher Angulo
 * @implNote Helper to use in all application to do generic operations
 */

public class Helper {


    /**
     * @implNote static method to generate random number
     * @param min minimum integer value
     * @param max maximum integer value
     * @return a random number between inputs number
     */
    public static int generateRandomNumber(int min, int max){
        return (int)(Math.random()*(max-min+1)+min);
    }

    /**
     * @implNote static method to generate random elements with a database in memory defined in list of objects
     * @param total number of elements
     * @return element list generated randomly
     */
    public static List<Object> generateRandomElements(int total){

        List<Object> elements = new ArrayList<>();

        List<Object> baseElements = new ArrayList<>();
        baseElements.add("Bear's");
        baseElements.add("Tiger's");
        baseElements.add("Wolf's");
        baseElements.add("Bee's");
        baseElements.add("Car's");
        baseElements.add("Dog's");
        baseElements.add("Snake's");
        baseElements.add("Python's");
        baseElements.add("Lion's");
        baseElements.add("Spider's");

        List<Object> positions = new ArrayList<>();
        positions.add("with");
        positions.add("on");
        positions.add("in");
        positions.add("beside");
        positions.add("above");
        positions.add("in front of");
        positions.add("under");
        positions.add("behind");
        positions.add("next to");

        List<Object> objects = new ArrayList<>();
        objects.add("Computer");
        objects.add("Book");
        objects.add("Ball");
        objects.add("Guitar");
        objects.add("Jacket");
        objects.add("Shirt");
        objects.add("Sweater");
        objects.add("Table");
        objects.add("Chair");
        objects.add("Flower");


        for (int i = 0; i < total ; i++) {
            Object baseElement = baseElements.get(generateRandomNumber(0,9));
            Object position = positions.get(generateRandomNumber(0,8));
            Object object = objects.get(generateRandomNumber(0,9));
            elements.add( generateRandomNumber(1,10) +" " + baseElement+" "+ position +" "+ object);
        }
        return elements;


    }

    /**
     * @implNote static method to validate total cards to create dobble cards
     * @param totalCards number of cards
     * @param elementsPerCard number of distinct elements that we identify for each card
     * @param totalCardsAuxiliary  number of cards auxiliary
     * @return return true if is valid total cards or  false if is the opposite
     */
    public static boolean isValidTotalCards(int totalCards,  int elementsPerCard, int totalCardsAuxiliary){
        return (totalCardsAuxiliary >= totalCards && totalCards>0)? true : false;
    }

    /**
     * @implNote static method to validate the order of projective plane in order to create cardsSet
     * the result It must be prime integer to return true
     * @param order number of order
     * @return valid if order of deck is valid
     */
    public static boolean  isValidOrder(int order)  {
        if(order<=1) {
            return false;
        }
        for (int i = 2; i<= order/2; i++)
        {
            if ((order % i) == 0)
            {
               return false;
            }
        }

        return true;
    }


}
