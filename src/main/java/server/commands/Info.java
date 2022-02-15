package server.commands;

import client.user.User;
import lib.collectionworker.CollectionManager;
import lib.utils.CollectionInfo;
import server.interfaces.Command;

public class Info implements Command {
    private CollectionManager collectionManager;
    private CollectionInfo collectionInfo;
    private User user;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        collectionInfo = new CollectionInfo();
    }

    @Override
    public String execute() {
       return collectionInfo.getInfoAboutCollection(collectionManager);
    }

    @Override
    public void setUserArgument(User user) {
        this.user=user;
    }

    @Override
    public String getDescription() {
        return "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
