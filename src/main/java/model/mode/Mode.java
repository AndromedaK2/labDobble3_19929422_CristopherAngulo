package model.mode;
import model.card.Card;

import java.util.List;

public class Mode implements  IMode {




    @Override
    public boolean spotit(Object element, Card firstCard, Card secondCard) {
        return firstCard.getElements().contains(element) && secondCard.getElements().contains(element);
    }

    @Override
    public void pass() {

    }

    @Override
    public void finish() {

    }
}
