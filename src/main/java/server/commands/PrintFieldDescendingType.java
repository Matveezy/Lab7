package server.commands;

import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import server.interfaces.Command;

import java.util.LinkedList;

public class PrintFieldDescendingType implements Command {
    private CollectionManager collectionManager;

    public PrintFieldDescendingType(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        LinkedList<Dragon> reverseDragons = collectionManager.reverseCollection();
        reverseDragons.stream()
                .forEach(dragon -> sb.append(dragon.getType() + "\n"));
        String result = sb.toString();
        sb.setLength(0);
        return result;
    }

    @Override
    public String getDescription() {
        return "Вывести значения поля type всех элементов в порядке убывания";
    }
}
