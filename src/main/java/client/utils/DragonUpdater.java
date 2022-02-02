package client.utils;

import lib.collection.*;
import lib.utils.CollectionInfo;

import java.util.Scanner;

public class DragonUpdater {

    private CollectionInfo collectionInfo;

    public DragonUpdater() {
        this.collectionInfo = new CollectionInfo();
    }

    public Dragon updateById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(collectionInfo.getFieldsName());
        System.out.println("Введите stop, когда захотите прервать изменение элемента коллекции!");
        System.out.println("Введите значения полей элемента коллекции :");
        String[] commandWords = new String[0];
        Dragon dragon = new Dragon();
        Coordinates coordinates = new Coordinates();
        DragonCave dragonCave = new DragonCave();
        dragon.setCoordinates(coordinates);
        dragon.setCave(dragonCave);
        do {
            try {
                commandWords = scanner.nextLine().trim().split("\\s+");
                if (commandWords.length == 1) {
                    updatedDragon(commandWords[0], "", dragon);
                } else {
                    dragon = updatedDragon(commandWords[0], commandWords[1], dragon);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Не указано поле или значение!");
            } catch (NullPointerException e2) {
                System.out.println("Поле не может быть задано пустой строкой!");
            } catch (NumberFormatException e3) {
                System.out.println("Формат не соответствует целочисленному!");
            }
        } while (!commandWords[0].equals("stop"));
        return dragon;
    }

    private Dragon updatedDragon(String field, String value, Dragon dragon) {
        switch (field) {
            case "name":
                if (value.equals("")) throw new NullPointerException();
                dragon.setName(value);
                System.out.println("Поле name изменено!");
                break;
            case "coordinate_x":
                if (value.equals("")) value = null;
                dragon.getCoordinates().setX(Integer.parseInt(value));
                System.out.println("Поле coordinate_x изменено!");
                break;
            case "coordinate_y":
                if (value.equals("")) value = null;
                dragon.getCoordinates().setY(Double.parseDouble(value));
                System.out.println("Поле coordinate_t изменено!");
                break;
            case "age":
                if (value.equals("")) {
                    dragon.setAge(null);
                }
                dragon.setAge(Integer.parseInt(value));
                System.out.println("Поле age изменено!");
                break;
            case "color":
                dragon.setColor(Color.valueOf(value.toUpperCase()));
                System.out.println("Поле color изменено!");
                break;
            case "type":
                dragon.setType(DragonType.valueOf(value.toUpperCase()));
                System.out.println("Поле type изменено!");
                break;
            case "character":
                dragon.setCharacter(DragonCharacter.valueOf(value.toUpperCase()));
                System.out.println("Поле character изменено!");
                break;
            case "cave_depth":
                if (value.equals("")) throw new NullPointerException();
                dragon.getCave().setDepth(Float.parseFloat(value));
                System.out.println("Поле cave_depth изменено!");
                break;
            case "cave_number_of_treasures":
                if (value.equals("")) throw new NullPointerException();
                dragon.getCave().setNumberOfTreasures(Float.parseFloat(value));
                System.out.println("Поле cave_number_of_treasures изменено!");
                break;
            case "stop":
                System.out.println("Запрос на изменение объекта на сервер отправлен!");
                break;
            default:
                System.out.println("Название поля введено неверно!");
                break;
        }
        return dragon;
    }
}
