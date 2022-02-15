package server.commands;

import client.user.User;
import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import server.database.DataBaseManager;
import server.interfaces.Command;
import server.interfaces.CommandWithDragons;

public class Add implements CommandWithDragons {

    private CollectionManager collectionManager;
    private Dragon dragon;
    private User user;


    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void setUserArgument(User user) {
        this.user=user;
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
       if (DataBaseManager.addDragon(dragon , user)) {
           collectionManager.insert(dragon);
           sb.append("Дракон добавлен в коллекцию!");
       } else {
           sb.append("При добавлении произошла ошибка!");
       }
        return sb.toString();
    }

    @Override
    public String getDescription() {
        return "Добавить новый элемент в коллекцию";
    }

    @Override
    public void setDragonAgrument(Dragon dragon) {
        this.dragon = dragon;
    }
}
