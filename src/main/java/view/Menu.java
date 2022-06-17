package view;

import common.Helper;
import model.game.DobbleGame;
import model.game.DobbleGameMode;
import model.game.InvalidOrderException;


import java.util.*;

public class Menu {

    public List<DobbleGame> dobbleGames = new ArrayList<>();
    private boolean closeMenu = false;
    public void run() throws InvalidOrderException {
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
    private void selectOption() throws InvalidOrderException {
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
                showGameStatus();
                break;
            case 5: closeMenu = true;
                break;
        }
    }
    private void createGame() throws InvalidOrderException {
       int  totalCards =  requestTotalCards();
       int elementsPerCard  =  requestElementsPerCard();
       List<Object> elements = requestElements(totalCards,elementsPerCard);

        for (Object element : elements) {
            System.out.println(element);
        }



       int playersNumber = requestPlayersNumber();
       DobbleGameMode dobbleGameMode = requestGameMode();

       DobbleGame dobbleGame = new DobbleGame(elements,elementsPerCard,totalCards, dobbleGameMode,playersNumber);
       dobbleGames.add(dobbleGame);
       System.out.println(dobbleGame);
    }
    private void registerPlayer(){
        DobbleGame dg = dobbleGames.get(0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario para registarlo en el juego");
        String username = scanner.next();
        if(dg.register(username)){
            System.out.println("Se ha registrado");
        }else{
            System.out.println("El usuario ya existe");
        }
        System.out.println(dg.getPlayers());
    }
    private void play(){

    }
    private void showGameStatus(){
        System.out.println(this.dobbleGames.get(0));
    }
    private List<Object> requestElements(int totalCards, int elementsPerCard){
        List<Object> elements = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escoger una de las siguientes opciones");
        System.out.println("1. Ingresar elementos y símbolos 1 por 1");
        System.out.println("2. Generar elementos y símbolos aleatorios");
        int option = scanner.nextInt();
        int order = elementsPerCard -1;
        int totalElements =  (order*order)+order+1;

        switch(option)
        {
            case 1:
                elements.addAll(generateElements(totalElements));
                break;
            case 2:
                elements.addAll(generateRandomElements(totalElements));
                break;
        }

        return elements;
    }
    private int requestTotalCards(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Comenzamos creando el mazo de cartas");
        System.out.println("Ingresar Cantidad total de Cartas con las que deseas jugar");
        return scanner.nextInt();
    }
    private int requestElementsPerCard() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingresar la cantidad de elementos por carta que deseas");
            int elementsPerCard = scanner.nextInt();
            Helper.isValidOrder(elementsPerCard);
            return elementsPerCard;
        } catch (InvalidOrderException ex) {
            System.out.println(ex.getMessage());
        }

        return 0;
    }
    private int requestPlayersNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresar la cantidad de jugadores para jugar");
        return scanner.nextInt();
    }
    private DobbleGameMode requestGameMode(){
        Scanner scanner = new Scanner(System.in);
        DobbleGameMode dobbleGameMode = null;
        System.out.println("Selecciona uno de los siguientes modos de juego");
        System.out.println("1- StackMode");
        System.out.println("2- EmptyHandsStackMode");
        System.out.println("Otros modo diponibles proximamente...");
        int option = scanner.nextInt();
        switch(option)
        {
            case 1:
                dobbleGameMode = DobbleGameMode.STACKMODE;
                break;
            case 2:
                dobbleGameMode = DobbleGameMode.EMPTYHANDSMODE;
                break;
        }
        return dobbleGameMode;

    }
    private  List<Object> generateRandomElements(int totalCards){
        return Helper.generateRandomElements(totalCards);
    }
    private  List<Object> generateElements(int totalCards){
        Scanner scanner = new Scanner(System.in);
        List<Object> elements = new ArrayList<>();
        for(int i= 0; i<totalCards; i++){
            System.out.println("Ingresar elemento o símbolo "+(i+1));
            String element = scanner.next();
            elements.add(element);
        }
        return elements;
    }

}
