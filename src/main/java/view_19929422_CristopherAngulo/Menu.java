package view_19929422_CristopherAngulo;

import common_19929422_CristopherAngulo.Helper;
import model_19929422_CristopherAngulo.game.DobbleGame;
import model_19929422_CristopherAngulo.mode.DobbleGameMode;
import model_19929422_CristopherAngulo.game.DobbleGameStatus;

import java.util.*;
import java.util.concurrent.TimeUnit;

/** @author Cristopher Angulo
 * @implNote This class represent the user interface o UI to interact with users.
 * @version 1.0
 */
public class Menu {

    //region attributes
    /**
     * @description List of dobble game. We can have more than one
     */
    private List<DobbleGame> dobbleGames = new ArrayList<>();
    /**
     * @description represent a boolean value to keep or close menu
     */
    private boolean closeMenu = false;

    /**
     * @description represent a boolean value to keep or close game menu
     */
    private boolean closeGameMenu = false;
    /**
     * @description represent a boolean value to know the state of current game
     */

    private boolean closeStartMenu = false;
    private boolean finishCurrentGame = false;
    /**
     * @description represent current dobble game
     */
    private DobbleGame currentDobbleGame = new DobbleGame();
    //endregion

    //region public methods
    /**
     * @implNote this method is the initial point to run the application
     */
    public void run()  {
        while(!closeMenu)
        {
            displayMenuOptions();
            selectOption();
        }
    }
    //endregion

    //region private methods

    /**
     * @implNote method to show the main options
     */
    private  void displayMenuOptions(){
        System.out.println("Bienvenido");
        System.out.println("Escoja su opción:");
        System.out.println("1) Crear un Juego");
        System.out.println("2) Seleccionar juego");
        System.out.println("3) Salir");

    }

    /**
     * @implNote method to get the user option and execute the action relationed
     */
    private void selectOption()  {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch(option)
        {
            case 1:
                createGame();
                break;
            case 2:
                displayDobbleGames();
                break;
            case 3: closeMenu = true;
                break;
        }
    }

    /**
     * @implNote method to create a game
     * @description
     * get element per card
     * get total cards
     * get elements one by one or random
     * get maximum players number
     * add game to the list of dobble games
     */
    private void createGame()  {
        System.out.println("Debe añadirle un nombre representativo a su juego por el cual pueda identificar");

        String name = requestGameName();
        System.out.println("\n*********************************\n");
        System.out.println("Seguimos creando el mazo de cartas");

        int  elementsPerCard = requestElementsPerCard();
        System.out.println("\n*********************************\n");
        int  totalCards = requestTotalCards(elementsPerCard);
        System.out.println("\n*********************************\n");

        List<Object> elements =  requestElements(totalCards,elementsPerCard);
        System.out.println("\n*********************************\n");

        int playersNumber = requestPlayersNumber();
        System.out.println("\n*********************************\n");

        DobbleGameMode dobbleGameMode = requestGameMode();
        System.out.println("\n*********************************\n");

        DobbleGame dobbleGame = new DobbleGame(elements,elementsPerCard,totalCards, dobbleGameMode,playersNumber,name);
        registerPlayer(dobbleGame);
        System.out.println("\n*********************************\n");

        dobbleGames.add(dobbleGame);
        System.out.println(dobbleGame);
    }

    /**
     * @implNote this methods display dobble games
     */
    private void displayDobbleGames(){
        closeGameMenu = false;
        while(!closeGameMenu){
            System.out.println("*********************************");
            System.out.println("Lista de juegos que has creado: \n");
            if(this.dobbleGames.size()>0){
                int aux = 0;
                for (int i= 0; i < dobbleGames.size(); i++) {
                    aux = i +1;
                    System.out.println(aux+") "+dobbleGames.get(i).getName()+"\n");
                }
                System.out.println((aux+1)+") Volver atras");
                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();
                if(option == aux+1){
                    closeGameMenu = true;
                }else{
                    this.selectDobbleGames(option);
                }
            }else{
                closeGameMenu = true;
                System.out.println("No existen juego creados\n");
            }
        }
    }

    /**
     * @implNote  this method select a game option
     * @param option int value accord select option
     */
    private void selectDobbleGames(int option){
        //Manual clone
        DobbleGame dobbleGameReference = dobbleGames.get(option-1);
        DobbleGame dobbleGame = new DobbleGame(
                dobbleGameReference.getDobble().getAllElements(),
                dobbleGameReference.getDobble().getElementsPerCard(),
                dobbleGameReference.getDobble().getMaximumTotalCards(),
                dobbleGameReference.getDobbleGameMode(),
                dobbleGameReference.getPlayersNumber(),
                dobbleGameReference.getName(),
                dobbleGameReference.getPlayers(),
                dobbleGameReference.getTurns()
               );
        this.currentDobbleGame = dobbleGame;
        this.displayStartGame();
    }

    /**
     * @implNote this method display to get started a game
     */
    private void displayStartGame(){
        closeStartMenu = false;
        System.out.println("\n*********************************\n");
        while(!closeStartMenu){
            System.out.println("1) Jugar");
            System.out.println("2) Demo Cpu vs Cpu");
            System.out.println("3) Volver atras");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    play();
                    closeStartMenu = true;
                    break;
                case 2:
                    simulatorCpuVsCpu();
                    closeStartMenu = true;
                    break;
                case 3:
                    closeStartMenu = true;
                    break;
            }

        }
    }

    /**
     * @implNote method to register player in the current game
     */
    private void registerPlayer(DobbleGame dobbleGame){
        Scanner scanner = new Scanner(System.in);
        String result = "";
        boolean error = false;
        for (int i = 0; i < dobbleGame.getPlayersNumber(); i++) {
            int aux = i +1;

            System.out.println("Ingrese  nombre de usuario del jugador "+aux+ " para registrarlo en el juego");
            String username = scanner.next();
            result = dobbleGame.register(username);
            System.out.println(result);

            while(result=="jugador ya existe"){
                System.out.println("Ingrese  nombre de usuario del jugador "+aux+ " para registrarlo en el juego");
                username = scanner.next();
                result = dobbleGame.register(username);
                System.out.println(result);
            }
        }
        System.out.println(dobbleGame.getPlayers());
    }

    /**
     * @implNote method to start to play
     */
    private void play(){
        finishCurrentGame = false;
        boolean start = true;
        System.out.println("\n*********************************\n");
        System.out.println("Juego Iniciado: \n");
        DobbleGame dobbleGame = this.currentDobbleGame;
        dobbleGame.setGameStatus(DobbleGameStatus.STARTED);

        try{
            while(!finishCurrentGame)
            {
                if(start){
                    dobbleGame.startGame();
                }
                start = false;
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
                        spotit(dobbleGame);
                        start = true;
                        break;
                    case 2:
                        passTurn(dobbleGame);
                        start = true;
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
                        this.endGame(dobbleGame);
                        break;
                }
            }
        } catch (IndexOutOfBoundsException ex){
            System.out.println("No hay suficientes cartas en el mazo para continuar");
            start = false;
            this.endGame(dobbleGame);

        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    /**
     * @implNote method that retrieve element in common writting by user
     * inside execute spotit function of concrete mode
     * @param dobbleGame representation of dobble game
     * @see DobbleGame
     */
    private void spotit(DobbleGame dobbleGame){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zona de juego: "+ dobbleGame.getCardsZone());
        System.out.println("Ingresar elemento en común entre las cartas");
        Object element = scanner.next();
        if(dobbleGame.spotit(element)){
            System.out.println("Acertaste,"+ element +" es el elemento en común");
        }else{
            System.out.println("Fallaste, no es el elemento en común");
        }
    }

    /**
     * @implNote  this method pass a turn
     * @param dobbleGame represent a game
     */
    private void passTurn(DobbleGame dobbleGame){
        dobbleGame.passTurn();
        System.out.println("Has pasado de turno");
    }

    /**
     * @implNote  this method finish game
     * @param dobbleGame
     */
    private void endGame(DobbleGame dobbleGame){
        System.out.println("\nEl ganador es: \n"+dobbleGame.getWinner());
        dobbleGame.endGame();
        this.currentDobbleGame = null;
    }

    /**
     * @implNote this method show game status
     */
    private void showGameStatus(){
        System.out.println(this.dobbleGames.get(0));
    }

    /**
     *@implNote  this method request game name to user
     * @return gamename
     */
    private String requestGameName(){
        Scanner scanner = new Scanner(System.in);
        boolean wenttocatch = false;
        String gameName = "";
        do{
            try{
                System.out.println("Ingresar Nombre del juego");
                gameName =  scanner.nextLine();;
                wenttocatch = true;
            }catch(Exception ex){
                System.out.println("Ingresar un valor válido");
            }
        } while (!wenttocatch);
        return gameName;
    }
    /**
     * @implNote this method request elements to user
     * @return elements or symbols to create cards
     */
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

    /**
     * @implNote this  method request total cards to user
     * @return total cards
     */
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
            }
        } while (!wenttocatch);
        return totalCards;
    }
    /**
     * @implNote  this method request elements per card to user
     * @return elements per card
     */
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
            }
        }while (!wenttocatch);

        return value;

    }

    /**
     * @implNote  this method request players number  to user
     * @return  players number
     */
    private int requestPlayersNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresar la cantidad de jugadores para jugar");
        return scanner.nextInt();
    }

    /**
     * @implNote  this method request game mode  to user
     * @return  game mode
     */
    private DobbleGameMode requestGameMode(){
        Scanner scanner = new Scanner(System.in);
        DobbleGameMode dobbleGameMode = null;
        System.out.println("Selecciona uno de los siguientes modos de juego");
        System.out.println("1- StackMode");
        //System.out.println("2- EmptyHandsStackMode");
        System.out.println("Otros modo diponibles proximamente...");
        int option = scanner.nextInt();
        switch(option)
        {
            case 1:
                dobbleGameMode = DobbleGameMode.STACKMODE;
                break;
            //case 2:
            //    dobbleGameMode = DobbleGameMode.EMPTYHANDSMODE;
            //    break;
        }
        return dobbleGameMode;

    }

    /**
     * @implNote  this method generate random elements
     * @param totalCards total cards
     * @return  elements
     * @see Helper
     */
    private  List<Object> generateRandomElements(int totalCards){
        return Helper.generateRandomElements(totalCards);
    }

    /**
     * @implNote  get elements
     * @param totalElements total elements as integer
     * @return elements
     */
    private  List<Object> generateElements(int totalElements){
        Scanner scanner = new Scanner(System.in);
        List<Object> elements = new ArrayList<>();
        String element;
        boolean isValidElement = true;
        for(int i= 0; i<totalElements; i++){

            System.out.println("Ingresar elemento o símbolo "+(i+1));
            element = scanner.next();
            while(element.isBlank() || element.isEmpty() || element == null || elements.contains(element)){
                if(element.isBlank() || element.isEmpty() || element == null){
                    System.out.println("No son permitidos elementos en blanco");
                }
                if(elements.contains(element)){
                    System.out.println("Es un elemento duplicado");
                }

                System.out.println("Ingresar elemento o símbolo "+(i+1));
                element = scanner.next();
            }

            elements.add(element);


        }
        for(int j = 0; j<elements.size();j++){
            System.out.println("Elemento: "+elements.get(j));
        }
        return elements;
    }

    /**
     * @implNote  this method is a simulator cpu vs cpu
     */
    private void simulatorCpuVsCpu(){
        finishCurrentGame = false;
        boolean start = true;
        System.out.println("\n*********************************\n");
        System.out.println("Juego Iniciado: \n");
        DobbleGame dobbleGame = this.currentDobbleGame;
        dobbleGame.setGameStatus(DobbleGameStatus.STARTED);

        try{
            while(!finishCurrentGame)
            {
                if(start){
                    dobbleGame.startGame();
                }
                start = false;
                System.out.println("Juega: "+ dobbleGame.getWhoseIsTurn());
                System.out.println("\n***************************************");
                System.out.println("Zona de juego: "+ dobbleGame.getCardsZone());
                System.out.println("\n***************************************");
                System.out.println("Escoja su opción:");
                System.out.println("1) Spotit");
                System.out.println("2) Pasar");

                TimeUnit.SECONDS.sleep(2);


                int option = Helper.generateRandomNumber(1,2);

                switch(option)
                {
                    case 1:
                        spotitCPU(dobbleGame);
                        start = true;
                        break;
                    case 2:
                        passTurn(dobbleGame);
                        start = true;
                        break;
                    case 3: finishCurrentGame = true;
                        this.endGame(dobbleGame);
                        break;
                }
            }
        } catch (IndexOutOfBoundsException ex){
            System.out.println("No hay suficientes cartas en el mazo para continuar");
            start = false;
            this.endGame(dobbleGame);

        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
    }


    /**
     * @implNote  this method is a special spoit random
     */
    private void spotitCPU(DobbleGame dobbleGame) {
        try{
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Ingresar elemento en común entre las cartas");

            List<Object> elements = dobbleGame.getCardsZone().get(0).getElements();
            Object element = elements.get(Helper.generateRandomNumber(0,elements.size()-1));
            if(dobbleGame.spotit(element)){
                System.out.println("Acertaste: "+ element +" es el elemento en común");
            }else{
                System.out.println("Fallaste: "+ element +" no es el elemento en común");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    //endregion


}
