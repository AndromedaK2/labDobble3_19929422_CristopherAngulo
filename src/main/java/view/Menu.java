package view;

import model.game.IDobbleGame;

import java.util.Scanner;

public class Menu {

    public IDobbleGame dobbleGame = null;
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
                createGame();
                break;
            case 2:
                registerPlayer();
                break;
            case 3:
                play();
                break;
            case 4:
                showStatusGame();
                break;
            case 5: closeMenu = true;
                break;
        }

    }


    private void createGame(){
        System.out.println("Comenzamos creando el mazo de cartas");
        System.out.println("Ingresar cantidad de elementos por carta");
        Scanner scanner = new Scanner(System.in);
        int order = scanner.nextInt();

        System.out.println("Cantidad total de Cartas con las que deseas jugar");
        System.out.println("Ingresar cantidad de elementos por carta");

    }

    private void registerPlayer(){

    }

    private void play(){

    }

    private void showStatusGame(){

    }

}
