package server.commands;

import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import server.interfaces.Command;
import server.interfaces.CommandWithDragons;

public class Add implements CommandWithDragons {

    private CollectionManager collectionManager;
    private Dragon dragon;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        collectionManager.insert(dragon);
        sb.append("Дракон добавлен в коллекцию!");
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
