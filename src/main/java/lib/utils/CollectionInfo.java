package lib.utils;

import lib.collection.Color;
import lib.collection.Dragon;
import lib.collection.DragonCharacter;
import lib.collection.DragonType;
import lib.collectionworker.CollectionManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class CollectionInfo {


    public String getInfoAboutCollection(CollectionManager collectionManager) {
        StringBuffer sb = new StringBuffer();
        sb.append("Дата создания коллекции " + collectionManager.getCreationCollectionDate() + "\n");
        sb.append("Количество элементов в коллеции " + collectionManager.getDragons().size() + "\n");
        return sb.toString();
    }

    public String show(CollectionManager collectionManager) {
        StringBuffer sb = new StringBuffer();
        if (collectionManager.getDragons().isEmpty()) {
            sb.append("Коллекция пуста!" + "\n");
        } else {
            collectionManager.getDragons()
                    .stream()
                    .sorted((o1 , o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                    .forEach(value -> sb.append(value + "\n"));
        }
        return sb.toString();
    }

    public String getFieldsName() {
        return "Список всех полей:\nname(String)\ncoordinate_x(Integer)\ncoordinate_y(Double)\nage(Integer)" +
                "\ncolor: " + Arrays.toString(Color.values()) + "\ntype: " + Arrays.toString(DragonType.values()) +
                "\ncharacter: " + Arrays.toString(DragonCharacter.values()) + "\ncave_depth(Float)\ncave_number_of_treasures(Float)\n";
    }


}

