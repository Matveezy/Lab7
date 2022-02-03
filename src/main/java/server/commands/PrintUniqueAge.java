package server.commands;

import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import server.interfaces.Command;

import java.util.HashSet;
import java.util.Set;

public class PrintUniqueAge implements Command {
    private CollectionManager collectionManager;
    private Set<Integer> ages;

    public PrintUniqueAge(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    private Set<Integer> createAgeSet() {
        ages = new HashSet<>();
        for (Dragon dragon : collectionManager.getDragons()) {
            ages.add(dragon.getAge());
        }
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
    public String getDescription() {
        return "Вывести уникальные значения поля age всех элементов в коллекции";
    }

}
