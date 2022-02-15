package server.commands;

import client.user.User;
import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import server.database.DataBaseManager;
import server.interfaces.CommandWithDragons;

public class AddIfMin implements CommandWithDragons {
    private Dragon dragon;
    private CollectionManager collectionManager;
    private User user;

    public AddIfMin(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void setUserArgument(User user) {
        this.user=user;
    }

    @Override
    public String execute() {
        Dragon minDragonFromCollection = collectionManager.findMinDragon(user);
        if (minDragonFromCollection==null){
            return "Минимального элемента нет!";
        }
        if (dragon.compareTo(minDragonFromCollection)>0){
            return "Значение этого элемента больше, чем значение минимального элемента коллекции!\nЭлемент не добавлен!";
        }
        if (dragon.compareTo(minDragonFromCollection)==0){
            return "Значения этого элемента равно значению минимального элемента коллекции!\nЭлемент не добавлен!";
        }
        if (dragon.compareTo(minDragonFromCollection)<0){
            if (DataBaseManager.addDragon(dragon , user)) {
                collectionManager.insert(dragon);
            } else {
                return "Произошла ошибка при добавлении в базу!";
            }
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
