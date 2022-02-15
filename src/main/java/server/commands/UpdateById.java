package server.commands;

import client.user.User;
import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;
import server.database.DataBaseManager;
import server.interfaces.CommandWithArguments;
import server.interfaces.CommandWithDragons;

public class UpdateById implements CommandWithDragons, CommandWithArguments {
    private Dragon dragon;
    private CollectionManager collectionManager;
    private String arguments;
    private User user;

    public UpdateById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        try {
            Dragon dragonFromCollection = collectionManager.getElementById(Long.parseLong(arguments));
            if (!collectionManager.getLoggedUserDragons(user, collectionManager.getDragons()).stream()
                    .anyMatch(dragon1 -> dragon1.equals(dragonFromCollection)) || dragonFromCollection == null) {
                return "У пользователя нет элемента с данным id!";
            }
            if (dragonFromCollection.getId() == -1) {
                return "Id не может быть меньше единицы!";
            }
            setChangesToDragon(dragon, dragonFromCollection);
            if (DataBaseManager.updateById(dragonFromCollection)) {
                return "Значение элемента обновлено!";
            }
        } catch (NumberFormatException e) {
            return "Id должен быть числом!";
        }
        return "Ошибка обновления";
    }

    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }

    @Override
    public String getDescription() {
        return "Обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void getArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void setDragonAgrument(Dragon dragon) {
        this.dragon = dragon;
    }

    public void setChangesToDragon(Dragon src, Dragon dest) {
        if (src.getName() != null) {
            dest.setName(src.getName());
        }
        if (src.getCoordinates().getX() != null && dest.getCoordinates().getX() != src.getCoordinates().getX()) {
            dest.getCoordinates().setX(src.getCoordinates().getX());
        }
        if (src.getCoordinates().getY() != null && dest.getCoordinates().getY() != src.getCoordinates().getY()) {
            dest.getCoordinates().setY(src.getCoordinates().getY());
        }
        if (src.getAge() != null) {
            dest.setAge(src.getAge());
        }
        if (src.getColor() != null) {
            dest.setColor(src.getColor());
        }
        if (src.getType() != null) {
            dest.setType(src.getType());
        }
        if (src.getCharacter() != null) {
            dest.setCharacter(src.getCharacter());
        }
        if (src.getCave().getDepth() != null && dest.getCave().getDepth() != src.getCave().getDepth()) {
            dest.getCave().setDepth(src.getCave().getDepth());
        }
        if (src.getCave().getNumberOfTreasures() != null && dest.getCave().getNumberOfTreasures() != src.getCave().getNumberOfTreasures()) {
            dest.getCave().setNumberOfTreasures(src.getCave().getNumberOfTreasures());
        }
    }
}
