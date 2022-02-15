package server.commands;

import client.user.User;
import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import lib.utils.CollectionInfo;
import server.database.DataBaseManager;
import server.interfaces.CommandWithDragons;

import java.util.LinkedList;

public class RemoveLower implements CommandWithDragons {

    private Dragon dragon;
    private CollectionManager collectionManager;
    private User user;

    public RemoveLower(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void setUserArgument(User user) {
        this.user=user;
    }

    @Override
    public String execute() {
        LinkedList<Dragon> needToRemoveDragons = new LinkedList<>();
        for (Dragon dragon : collectionManager.getLoggedUserDragons(user, collectionManager.getDragons())) {
            if (dragon.compareTo(this.dragon) < 0) {
                needToRemoveDragons.add(dragon);
            }
        }
        needToRemoveDragons.stream()
                        .forEach(dragon1 -> DataBaseManager.removeById(dragon1.getId()));
        collectionManager.getDragons().removeAll(needToRemoveDragons);
        return "Все элементы , меньше заданного, удалены из базы!";
    }

    @Override
    public String getDescription() {
        return "Удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public void setDragonAgrument(Dragon dragon) {
        this.dragon = dragon;
    }
}
