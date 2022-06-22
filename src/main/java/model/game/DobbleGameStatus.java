package model.game;

public enum DobbleGameStatus {
    CREATED("Juego Creado"),STARTED("Juego Inicializado"),FINISHED("Juego Finalizado");
    private String statusName;

    DobbleGameStatus(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString(){
        return statusName;
    }
}
