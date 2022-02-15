package server.commands;

import client.user.User;
import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import server.interfaces.Command;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PrintUniqueAge implements Command {
    private CollectionManager collectionManager;
    private Set<Integer> ages;
    private User user;
    public PrintUniqueAge(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    private Set<Integer> createAgeSet() {
        ages = collectionManager.getDragons().stream()
                .map(Dragon::getAge)
                .collect(Collectors.toSet());
        return ages;
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        createAgeSet().stream()
                .forEach(age -> sb.append(age + "\n"));
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
        return "Вывести уникальные значения поля age всех элементов в коллекции";
    }

}
