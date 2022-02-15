package server.commands;

import client.user.User;
import lib.collectionworker.CollectionManager;
import server.interfaces.Command;

public class Exit implements Command {
    private CollectionManager collectionManager;
    private String inputFile;
    private User user;

    public Exit(CollectionManager collectionManager, String inputFile) {
        this.collectionManager = collectionManager;
        this.inputFile = inputFile;
    }

    @Override
    public String execute() {
        return "Вы отключились от сервера!";
    }

    @Override
    public void setUserArgument(User user) {
        this.user=user;
    }

    @Override
    public String getDescription() {
        return "Завершить работу программы";
    }
}
