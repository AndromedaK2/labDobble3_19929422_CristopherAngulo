package console;

import view.Menu;

/**
 * @author Cristopher angulo
 * @version 1.0
 */
public class Main {

    /**
     * @implNote this main class create an instance of menu and after that the main run menu
     * @param args
     * @see Menu
     */
    public static void main(String[] args)  {
        Menu menu = new Menu();
        menu.run();
    }

}