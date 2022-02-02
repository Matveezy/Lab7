package server.commands;

import lib.collectionworker.CollectionManager;
import server.interfaces.Command;

public class Clear implements Command {
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        collectionManager.clear();
        return "Коллекция очищена!";
    }

    @Override
    public String getDescription() {
        return "Очистить коллекцию";
    }
}
