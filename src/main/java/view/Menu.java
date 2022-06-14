package view;

import common.Helper;
import model.game.DobbleGame;


import java.util.*;

public class Menu {

    public List<DobbleGame> dobbleGames = null;

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

        requestElements();

    }

    private void registerPlayer(){

    }

    private void play(){

    }

    private void showStatusGame(){

    }

    private List<Object> requestElements(){
        List<Object> elements = new ArrayList<>();
        int totalCards = getTotalCardsEnterByUser();
        int elementsPerCard = getElementsPerCardEnterByUser();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escoger una de las siguientes opciones");
        System.out.println("1. Ingresar elementos y símbolos 1 por 1");
        System.out.println("2. Generar elementos y símbolos aleatorios");
        int option = scanner.nextInt();

        switch(option)
        {
            case 1:
                generateElements(totalCards).forEach(element-> elements.add(element));
                break;
            case 2:
                generateRandomElements(totalCards).forEach(element-> elements.add(element));
                break;
        }

        return elements;
    }

    private int getTotalCardsEnterByUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Comenzamos creando el mazo de cartas");
        System.out.println("Cantidad total de Cartas con las que deseas jugar");
        int totalCards = scanner.nextInt();
        return totalCards;
    }
    private int getElementsPerCardEnterByUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresar la cantidad de elementos por carta que deseas");
        int elementsPerCard = scanner.nextInt();
        return elementsPerCard;
    }
    private  List<Object> generateRandomElements(int totalCards){
        List<Object> elements = Helper.generateRandomElements(totalCards);
        for (int j = 0; j <elements.size(); j++ ){
            System.out.println(elements.get(j));
        }
        return elements;
    }
    public  List<Object> generateElements(int totalCards){
        Scanner scanner = new Scanner(System.in);
        List<Object> elements = new ArrayList<>();
        for(int i= 0; i<totalCards; i++){
            System.out.println("Ingresar elemento o símbolo "+(i+1));
            String element = scanner.next();
            elements.add(element);
        }

        for (int j = 0; j <elements.size(); j++ ){
            System.out.println(elements.get(j));
        }
        return elements;
    }

}
