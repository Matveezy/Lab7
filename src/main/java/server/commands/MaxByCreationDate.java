package server.commands;

import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import lib.utils.DateParser;
import server.interfaces.Command;

public class MaxByCreationDate implements Command {
    private CollectionManager collectionManager;
    private DateParser dateParser;

    public MaxByCreationDate(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        dateParser = new DateParser();
    }

    @Override
    public String execute() {
        if (collectionManager.getSize() == 0) {
            return "Коллекция пустая!";
        }
        if (collectionManager.getSize() == 1) {
           return collectionManager.getDragons().get(0).toString();

        }
        return findDragonWithMaxCreationDay(collectionManager).toString();
    }

    @Override
    public String getDescription() {
        return "Вывести любой объект из коллекции, значение поля creationDate которого является максимальным";
    }

    private Dragon findDragonWithMaxCreationDay(CollectionManager collectionManager) {
        Dragon firstDragon = collectionManager.getDragons().get(0);
        Integer maxYear = dateParser.getYear(firstDragon);
        Integer maxMonth = 01;
        Integer maxDay = dateParser.getDay(firstDragon);
        Dragon dragon = collectionManager.getDragons().get(0);
        for (Dragon val : collectionManager.getDragons()) {
            if (val.getId()==firstDragon.getId()) continue;
            if (maxYear < dateParser.getYear(val)) {
                maxYear = dateParser.getYear(val);
                dragon = val;
                continue;
            } else if (maxYear > dateParser.getYear(val)) continue;
            else if (maxYear == dateParser.getYear(val)) {
                if (maxMonth < dateParser.getMonth(val)) {
                    maxMonth = dateParser.getMonth(val);
                    dragon = val;
                    continue;
                } else if (maxMonth > dateParser.getMonth(val)) continue;
                else if (maxMonth == dateParser.getMonth(val)) {
                    if (maxDay < dateParser.getDay(val)) {
                        maxDay = dateParser.getDay(val);
                        dragon = val;
                        continue;
                    } else if (maxDay > dateParser.getDay(val)) continue;
                    ;
                }
            }
        }
        return dragon;
    }
}
