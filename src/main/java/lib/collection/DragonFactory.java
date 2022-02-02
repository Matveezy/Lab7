package lib.collection;

import lib.collectionworker.CollectionManager;
import lib.userio.UserIO;
import lib.utils.DragonFieldsReader;

import java.time.LocalDate;
import java.util.Scanner;

public class DragonFactory {
    private long id = 1;
    Scanner scanner = new Scanner(System.in);
    private UserIO userIO = new UserIO(scanner);
    private DragonFieldsReader dragonFieldsReader = new DragonFieldsReader();


    public Dragon createDragon() {
        return new Dragon(getId(),
                dragonFieldsReader.readName(),
                dragonFieldsReader.readCoordinates(),
                LocalDate.now(),
                dragonFieldsReader.readAge(),
                dragonFieldsReader.readColor(),
                dragonFieldsReader.readDragonType(),
                dragonFieldsReader.readDragonCharacter(),
                dragonFieldsReader.readDragonCave());
    }


    public void setId(long id) {
        this.id = id;
    }

    private long getId() {
        return id++;
    }

    public long getFirstFreeId(CollectionManager collectionManager) {
        long newid = 1;
        for (Dragon val : collectionManager.getDragons()) {
            if (newid != val.getId()) {
                return newid;
            }
            newid++;
        }
        return newid;
    }
}
