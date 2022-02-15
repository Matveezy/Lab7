package server.commands;

import client.user.User;
import lib.collectionworker.CollectionManager;
import server.interfaces.Command;

public class Clear implements Command {
    private CollectionManager collectionManager;
    private User user;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
       return collectionManager.clear(user);
    }

    @Override
    public void setUserArgument(User user) {
        this.user=user;
    }

    @Override
    public String getDescription() {
        return "Очистить коллекцию";
    }
}
