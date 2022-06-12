package model.deck;

public interface ICard {
     void addElement(Object element);
     Object getFirstElement();
     Object getLastElement();
     Boolean compareUniqueElements(Card FirstCard);
     String toString();
}
