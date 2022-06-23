package view;

import common.Helper;
import model.game.DobbleGame;
import model.game.DobbleGameMode;
import model.game.DobbleGameStatus;
import model.mode.IMode;
import model.mode.StackMode;

import java.util.*;

/** @author Cristopher Angulo
 * @implNote This class represent the user interface.
 * We retrieve and output data to the final user with in concrete operations
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
     * @description represent a boolean value to know the state of current game
     */
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
        System.out.println("3) Jugar (debe seleccionar un juego antes)");
        System.out.println("4) Salir");

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
            case 3:
                play();
                break;
            case 4: closeMenu = true;
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

        System.out.println("Seguimos creando el mazo de cartas");

        int  elementsPerCard = requestElementsPerCard();
        int  totalCards = requestTotalCards(elementsPerCard);

        List<Object> elements =  requestElements(totalCards,elementsPerCard);
        int playersNumber = requestPlayersNumber();
        DobbleGameMode dobbleGameMode = requestGameMode();

        DobbleGame dobbleGame = new DobbleGame(elements,elementsPerCard,totalCards, dobbleGameMode,playersNumber,name);
        registerPlayer(dobbleGame);
        dobbleGames.add(dobbleGame);
        System.out.println(dobbleGame);
    }

    private void displayDobbleGames(){
        System.out.println("Lista de juegos que has creado");
        if(this.dobbleGames.size()>0){
            for (int i = 0; i < dobbleGames.size(); i++) {
                int aux = i+1;
                System.out.println(aux+"- "+dobbleGames.get(i).getName()+"\n");
            }
            this.selectDobbleGames();
        }else{
            System.out.println("No existen juego creados\n");
        }

    }
    private void selectDobbleGames(){
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        //Manual clone object

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

    }

    /**
     * @implNote method to register user in the current game
     */
    private void registerPlayer(DobbleGame dobbleGame){
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < dobbleGame.getPlayersNumber(); i++) {
            int aux = i +1;
            System.out.println("Ingrese  nombre de usuario del jugador "+aux+ " para registrarlo en el juego");
            String username = scanner.next();
            if(dobbleGame.register(username)){
                System.out.println("Se ha registrado");
            }else{
                System.out.println("El usuario no se puede registrar");
            }
            System.out.println(dobbleGame.getPlayers());
        }
    }

    /**
     * @implNote method to start to play
     */
    private void play(){
        boolean start = true;
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
    private void passTurn(DobbleGame dobbleGame){
        dobbleGame.passTurn();
    }
    private void endGame(DobbleGame dobbleGame){
        System.out.println("\nEl ganador es: \n"+dobbleGame.getWinner());
        dobbleGame.endGame();
        this.currentDobbleGame = null;
    }
    private void showGameStatus(){
        System.out.println(this.dobbleGames.get(0));
    }
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
    private  List<Object> generateRandomElements(int totalCards){
        return Helper.generateRandomElements(totalCards);
    }
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

    //endregion


}
