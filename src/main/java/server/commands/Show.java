package server.commands;

import lib.collectionworker.CollectionManager;
import lib.utils.CollectionInfo;
import server.interfaces.Command;

public class Show implements Command {
    private StringBuffer sb;
    private CollectionManager collectionManager;
    private CollectionInfo collectionInfo;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        collectionInfo = new CollectionInfo();
        sb = new StringBuffer();
    }

    @Override
    public String execute() {
        return collectionInfo.show(collectionManager);
    }

    @Override
    public String getDescription() {
        return "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
