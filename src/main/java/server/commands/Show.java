package server.commands;

import client.user.User;
import lib.collectionworker.CollectionManager;
import lib.utils.CollectionInfo;
import server.interfaces.Command;

public class Show implements Command {
    private StringBuffer sb;
    private CollectionManager collectionManager;
    private CollectionInfo collectionInfo;
    private User user;

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

    @Override
    public void setUserArgument(User user) {
        this.user=user;
    }
}
