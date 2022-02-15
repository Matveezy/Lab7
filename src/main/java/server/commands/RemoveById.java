package server.commands;

import client.user.User;
import lib.collectionworker.CollectionManager;
import server.interfaces.CommandWithArguments;

public class RemoveById implements CommandWithArguments {
    private String agruments;
    private CollectionManager collectionManager;
    private User user;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        try {
            long id = Long.parseLong(this.agruments);
            if (id <= 0) return "Некорректно введен id!";
            if (!collectionManager.isElementInCollection(id, user)) {
                return "У пользователя нет объекта с таким id!";
            }
           if (collectionManager.removeById(id)) {
               return "Элемент с id " + id + " успешно удален!";
           }
        } catch (NumberFormatException e) {
            return "Id должен быть числом!";
        }
        return "";
    }

    @Override
    public void setUserArgument(User user) {
        this.user = user;
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
