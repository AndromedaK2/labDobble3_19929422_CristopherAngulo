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
        System.out.println("Ingrese una opción");
        System.out.println("1) Agregar cliente");
        System.out.println("2) Agregar producto");
        System.out.println("3) Salir");
    }

    private void selectOption()
    {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch(option)
        {

            case 3: closeMenu = true;
                break;
        }

    }

}
