package model.deck;

public interface ICard {
    public void addElement(Object element);

    public Object GetFirstElement();

    public Object GetLastElement();

    public Boolean CompareUniqueElements(Card FirstCard);

    @Override
    public String toString();
}
