package server.commands;

import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import lib.utils.CollectionInfo;
import server.interfaces.CommandWithDragons;

import java.util.LinkedList;

public class RemoveLower implements CommandWithDragons {

    private Dragon dragon;
    private CollectionManager collectionManager;

    public RemoveLower(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        LinkedList<Dragon> needToRemoveDragons = new LinkedList<>();
        for (Dragon dragon : collectionManager.getDragons()) {
            if (dragon.compareTo(this.dragon) < 0) {
                needToRemoveDragons.add(dragon);
            }
        }
        collectionManager.getDragons().removeAll(needToRemoveDragons);
        return "Все элементы , меньше заданного, удалены из коллекции!";
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
