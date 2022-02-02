package server.commands;

import lib.collectionworker.CollectionManager;
import server.interfaces.Command;

public class Exit implements Command {
    private CollectionManager collectionManager;
    private String inputFile;

    public Exit(CollectionManager collectionManager, String inputFile) {
        this.collectionManager = collectionManager;
        this.inputFile = inputFile;
    }

    @Override
    public String execute() {
        collectionManager.save();
        return "Вы отключились от сервера!";
    }

    @Override
    public String getDescription() {
        return "Завершить программу (без сохранения в файл)";
    }
}
