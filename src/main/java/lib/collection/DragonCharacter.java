package lib.collection;

import java.io.Serializable;

public enum DragonCharacter implements Serializable {
    CUNNING("cunning"),
    WISE("wise"),
    CHAOTIC("chaotic"),
    CHAOTIC_EVIL("chaotic_evil");

    String dragonCharacter;

    DragonCharacter(String dragonCharacter){
        this.dragonCharacter = dragonCharacter;
    }
}
