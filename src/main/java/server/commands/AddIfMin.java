package server.commands;

import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import server.interfaces.CommandWithDragons;

public class AddIfMin implements CommandWithDragons {
    private Dragon dragon;
    private CollectionManager collectionManager;

    public AddIfMin(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }



    @Override
    public String execute() {
        Dragon minDragonFromCollection = collectionManager.findMinDragon();
        if (dragon.compareTo(minDragonFromCollection)>0){
            return "Значение этого элемента больше, чем значение минимального элемента коллекции!\nЭлемент не добавлен!";
        }
        if (dragon.compareTo(minDragonFromCollection)==0){
            return "Значения этого элемента равно значению минимального элемента коллекции!\nЭлемент не добавлен!";
        }
        if (dragon.compareTo(minDragonFromCollection)<0){
            collectionManager.insert(dragon);
            return "Элемент добавлен!";
        }
        return null;
    }

    @Override
    public String getDescription() {
        return "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }

    @Override
    public void setDragonAgrument(Dragon dragon) {
        this.dragon = dragon;
    }
}
