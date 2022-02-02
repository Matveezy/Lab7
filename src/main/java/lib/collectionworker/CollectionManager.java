package lib.collectionworker;

import lib.collection.*;
import lib.file.FileWorker;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

@XmlRootElement(name = "dragons")
public class CollectionManager {

    private LinkedList<Dragon> dragons;
    private DragonFactory dragonFactory;
    private LocalDate creationCollectionDate;


    public CollectionManager() {
        creationCollectionDate = LocalDate.now();
        dragons = new LinkedList<Dragon>();
        dragonFactory = new DragonFactory();
    }


    /**
     * Методы , реализующие команды пользователя
     */


    public void insert(Dragon dragon) {
        long checkId = dragon.getId();
        if (checkId == 0) checkId++;
        boolean isContains = false;
        for (Dragon val : getDragons()) {
            if (isElementInCollection(checkId)) ++checkId;
        }
        dragon.setId(checkId);
        dragons.add(dragon);
        getDragonFactory().setId(checkId++);
    }


    public void updateById(long id, String field, String value) {
        switch (field) {
            case "name": {
                if (value.equals("")) throw new NullPointerException();
                getElementById(id).setName(value);
                System.out.println("Поле изменено!");
                break;
            }
            case "coordinate_x": {
                if (value.equals("")) value = null;
                getElementById(id).getCoordinates().setX(Integer.parseInt(value));
                System.out.println("Поле изменено!");
                break;
            }
            case "coordinate_y": {
                if (value.equals("")) value = null;
                getElementById(id).getCoordinates().setY(Double.parseDouble(value));
                System.out.println("Поле изменено!");
                break;
            }
            case "age": {
                if (value.equals("")) {
                    getElementById(id).setAge(null);
                }
                getElementById(id).setAge(Integer.parseInt(value));
                System.out.println("Поле изменено!");
                break;
            }
            case "color": {
                getElementById(id).setColor(Color.valueOf(value.toUpperCase(Locale.ROOT)));
                System.out.println("Поле изменено!");
                break;
            }

            case "type": {
                getElementById(id).setType(DragonType.valueOf(value.toUpperCase(Locale.ROOT)));
                System.out.println("Поле изменено!");
                break;
            }

            case "character": {
                getElementById(id).setCharacter(DragonCharacter.valueOf(value.toUpperCase(Locale.ROOT)));
                System.out.println("Поле изменено!");
                break;
            }

            case "cave_depth": {
                if (value.equals("")) throw new NullPointerException();
                getElementById(id).getCave().setDepth(Float.parseFloat(value));
                System.out.println("Поле изменено!");
                break;
            }

            case "cave_number_of_treasures": {
                if (value.equals("")) throw new NullPointerException();
                getElementById(id).getCave().setNumberOfTreasures(Float.parseFloat(value));
                System.out.println("Поле изменено!");
                break;
            }
            case "stop": {
                System.out.println("Объект изменен!");
                break;
            }
            default: {
                System.out.println("Значение поля введено неверно!");
                break;
            }
        }

    }


    public boolean removeById(long id) {
        if (id < 1 && id > dragons.size()) {
            return false;
        }
        for (Dragon val : dragons) {
            if (val.getId() == id) {
                dragons.remove(val);
                dragonFactory.setId(dragonFactory.getFirstFreeId(this));
                return true;
            }
        }
        return false;
    }


    public void clear() {
        dragons.clear();
        dragonFactory.setId(1);
    }


    public void save() {
        FileWorker fileWorker = new FileWorker(this, this.getDragonFactory());
        fileWorker.saveToXml();
    }

    /**
     * Вспомогательные методы(геттеры и сеттеры)
     *
     * @param list
     */

    @XmlElement(name = "dragon")
    public void setDragons(LinkedList<Dragon> list) {
        dragons = list;
    }


    public DragonFactory getDragonFactory() {
        return dragonFactory;
    }

    public int getSize() {
        return dragons.size();
    }

    public LinkedList<Dragon> getDragons() {
        return dragons;
    }

    public void setDragonFactory(DragonFactory dragonFactory) {
        this.dragonFactory = dragonFactory;
    }

    public LocalDate getCreationCollectionDate() {
        return creationCollectionDate;
    }


    /**
     * Вспомогательные методы с более сложной логикой
     */

    public Dragon findMinDragon() {
        Dragon minDragon = dragons.get(0);
        for (Dragon vals : dragons) {
            if (vals.compareTo(minDragon) > 0) minDragon = vals;
        }
        return minDragon;
    }

    public LinkedList<Dragon> reverseCollection() {
        LinkedList<Dragon> newDragons = new LinkedList<>();
        int count = 0;
        Iterator<Dragon> iterator = dragons.descendingIterator();
        while (iterator.hasNext() && count < dragons.size()) {
            newDragons.add(iterator.next());
            count++;
        }
        return newDragons;
    }

    public boolean isElementInCollection(long id) {
        if (id < 1) {
            return false;
        }
        for (Dragon vals : this.getDragons()) {
            if (vals.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Dragon getElementById(long id) {
        if (id < 1) {
            Dragon dragon = new Dragon();
            dragon.setId(-1);
            return dragon;
        }
        Dragon result = dragons.stream()
                .filter(dragon -> dragon.getId() == id)
                .findFirst()
                .get();
        return result;
    }
}
