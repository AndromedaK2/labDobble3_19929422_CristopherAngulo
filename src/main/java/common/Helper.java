package common;

public class Helper {

    public static int generateRandomNumber(){
        int min = 1;
        int max = 1000;
        return (int)(Math.random()*(max-min+1)+min);
    }


}
