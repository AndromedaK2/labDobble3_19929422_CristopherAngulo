package model.game;

public class InvalidOrderException extends Exception{
    public InvalidOrderException(){
        super("Elementos inválidos para crear el mazo de cartas");
    }
}
