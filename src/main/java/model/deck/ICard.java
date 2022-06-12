package model.deck;

public interface ICard {
     void addElement(Object element);
     Object GetFirstElement();
     Object GetLastElement();
     Boolean CompareUniqueElements(Card FirstCard);
     String toString();
}
