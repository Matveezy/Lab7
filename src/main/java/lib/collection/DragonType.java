package lib.collection;

import java.io.Serial;
import java.io.Serializable;

public enum DragonType implements Serializable {
    WATER("water"),
    UNDERGROUND("underground"),
    AIR("air"),
    FIRE("fire");

    String dragonType;

    DragonType(String dragonType){
        this.dragonType=dragonType;
    }
}
