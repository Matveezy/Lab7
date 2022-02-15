package server.commands;

import client.user.User;
import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import server.interfaces.Command;

import java.util.LinkedList;

public class PrintFieldDescendingType implements Command {
    private CollectionManager collectionManager;
    private User user;

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
    public void setUserArgument(User user) {
        this.user=user;
    }

    @Override
    public String getDescription() {
        return "Вывести значения поля type всех элементов в порядке убывания";
    }
}
