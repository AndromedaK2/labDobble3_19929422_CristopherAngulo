package view;

import common.Helper;
import model.game.DobbleGame;
import model.game.DobbleGameMode;
import model.game.DobbleGameStatus;
import model.mode.IMode;
import model.mode.StackMode;


import java.util.*;

public class Menu {

    private List<DobbleGame> dobbleGames = new ArrayList<>();

    private int elementsPerCard;
    private boolean closeMenu = false;
    private boolean finishCurrentGame = false;
    public void run()  {
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
        System.out.println("3) Iniciar Partida");
    }
    private void selectOption()  {
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
            case 4: closeMenu = true;
                break;
        }
    }
    private void createGame()  {
        System.out.println("Comenzamos creando el mazo de cartas");
        int  elementsPerCard = requestElementsPerCard();
        int  totalCards = requestTotalCards(elementsPerCard);

        List<Object> elements =  requestElements(totalCards,elementsPerCard);

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
        DobbleGame dobbleGame = this.dobbleGames.get(0);
        dobbleGame.setGameStatus(DobbleGameStatus.STARTED);
        StackMode mode = (StackMode) dobbleGame.getMode();
        mode.startGame(dobbleGame);
        while(!finishCurrentGame)
        {
            System.out.println("Juego Iniciado: ");
            System.out.println("Juega: "+ dobbleGame.getWhoseIsTurn());
            System.out.println("Escoja su opción:");
            System.out.println("1) Spotit");
            System.out.println("2) Pasar");
            System.out.println("3) Ver estado del juego");
            System.out.println("4) Ver zona de cartas");
            System.out.println("5) ¿De quién es el turno?");
            System.out.println("6) Terminar juego");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch(option)
            {
                case 1:
                    spoit(mode,dobbleGame);
                    break;
                case 2:

                    break;
                case 3:
                    System.out.println("Estado del juego: "+ dobbleGame);
                    break;
                case 4:
                    System.out.println("Zona de juego: "+ dobbleGame.getCardsZone());
                    break;
                case 5:
                    System.out.println("El turno es de: "+ dobbleGame.getWhoseIsTurn());
                    break;
                case 6: finishCurrentGame = true;
                    this.endGame();
                    break;
            }

        }


    }

    private void spoit(IMode mode, DobbleGame dobbleGame){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zona de juego: "+ dobbleGame.getCardsZone());
        System.out.println("Ingresar elemento en común entre las cartas");
        Object element = scanner.next();
        if(dobbleGame.spotit(element)){
            System.out.println("Acción exitosa");
        }else{
            System.out.println("Acción fallida");

        }
    }

    private void endGame(){

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
    private int requestTotalCards(int elementsPerCard){
        Scanner scanner = new Scanner(System.in);
        boolean isValidTotalCards = false;
        boolean wenttocatch = false;
        int order = elementsPerCard-1;
        int totalCardsAuxiliary = elementsPerCard + order * order;
        int totalCards = 0;
        do{
            try{
                while (!isValidTotalCards){
                    System.out.println("Ingresar Cantidad total de Cartas con las que deseas jugar");
                    totalCards =  scanner.nextInt();
                    isValidTotalCards = Helper.isValidTotalCards(totalCards,elementsPerCard,totalCardsAuxiliary);

                    if(!isValidTotalCards){
                        System.out.println("La cantidad de cartas debe ser mayor a cero y menor o igual a "+totalCardsAuxiliary);
                    }else{
                        wenttocatch = true;
                    }
                }
            }catch(InputMismatchException ex){
                System.out.println("Ingresar un valor entero válido");
                scanner.nextLine();
            }
        } while (!wenttocatch);
        return totalCards;
    }
    private int requestElementsPerCard() {
        boolean isValidElementsPerCard = false;
        boolean wenttocatch = false;
        int value = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            try{
                while(!isValidElementsPerCard){
                    System.out.println("Ingresar la cantidad de elementos por carta que deseas");
                    value = scanner.nextInt();
                    isValidElementsPerCard = Helper.isValidOrder(value-1);
                    if(!isValidElementsPerCard){
                        System.out.println("La cantidad de elementos es inválida para crear un mazo de cartas");
                    }else{
                        wenttocatch = true;
                    }
                }
            }catch (InputMismatchException  ex){
                System.out.println("Ingresar un valor entero válido");
                scanner.nextLine();
            }
        }while (!wenttocatch);

        return value;

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
