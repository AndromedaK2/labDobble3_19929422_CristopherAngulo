package model.player;

import model.deck.Dobble;

import java.util.Objects;

public class Player implements IPlayer {
    private String username;
    private Dobble dobble = new Dobble();
    private int points = 0;

    public String getUsername() {
        return username;
    }

    public void setDobble(Dobble dobble) {
        this.dobble = dobble;
    }

    public Player(String username) {
        this.username = username;
    }

    public Dobble getDobble() {
        return dobble;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        calculatePoints();
        return "Jugador: \n" +
                "Nombre de usuario='" + username + '\'' +
                ",\n" + dobble.toString() +
                ", points=" + points ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return points == player.points && Objects.equals(username, player.username) && Objects.equals(dobble, player.dobble);
    }

    private void calculatePoints(){
        for (int i = 0; i < dobble.dobbleCards.size(); i++) {
            points = points + 1;
        }
    }


}
