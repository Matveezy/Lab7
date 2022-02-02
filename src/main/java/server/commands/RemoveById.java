package server.commands;

import lib.collectionworker.CollectionManager;
import server.interfaces.CommandWithArguments;

public class RemoveById implements CommandWithArguments {
    private String agruments;
    private CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        try {
            long id = Long.parseLong(this.agruments);
            if (id <= 0) return "Некорректно введен id!";
            if (collectionManager.isElementInCollection(id)) {
                collectionManager.removeById(id);
                return "Элемент с id " + id + " успешно удален!";
            }
            return "Элемента с данным id нет в коллекции!";
        } catch (NumberFormatException e){
            return "Id должен быть числом!";
        }
    }

    @Override
    public String getDescription() {
        return "Удалить элемент из коллекции по его id";
    }

    @Override
    public void getArguments(String arguments) {
        this.agruments = arguments;
    }
}
