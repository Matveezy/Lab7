package server.commands;

import client.user.User;
import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import server.interfaces.Command;

public class Head implements Command {
    private CollectionManager collectionManager;
    private Dragon head;
    private User user;

    public Head(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
       if (collectionManager.getSize() != 0){
           head = collectionManager.getDragons().get(0);
           return head.toString();
       }
       else return "Коллекция пуста!\nКоманда head не может быть вызвана!";
    }

    @Override
    public void setUserArgument(User user) {
        this.user=user;
    }

    @Override
    public String getDescription() {
        return "Вывести первый элемент коллекции";
    }
}
