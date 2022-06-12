package view;

import java.util.Scanner;

public class Menu {

    private boolean closeMenu = false;
    public void run(){
        while(!closeMenu)
        {
            displayMenuOptions();
            selectOption();
        }
    }
    private  void displayMenuOptions(){
        System.out.println("Bienvenido");
        System.out.println("Escoja su opción:");
        System.out.println("1) Crear un Juego");
        System.out.println("2) Registrar jugador");
        System.out.println("3) Jugar");
        System.out.println("4) Visualizar estado completo del juego");

    }

    private void selectOption()
    {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch(option)
        {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4: closeMenu = true;
                break;
        }

    }

}
