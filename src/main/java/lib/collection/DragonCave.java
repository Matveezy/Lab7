package lib.collection;

import java.io.Serializable;

public class DragonCave implements Serializable {

    private Float depth; //Поле не может быть null
    private Float numberOfTreasures; //Поле не может быть null, Значение поля должно быть больше 0

    public DragonCave(Float depth, Float numberOfTreasures) {
        this.depth = depth;
        this.numberOfTreasures = numberOfTreasures;
    }

    public DragonCave() {
    }

    public Float getDepth() {
        return depth;
    }

    public Float getNumberOfTreasures() {
        return numberOfTreasures;
    }

    public void setDepth(Float depth) {
        this.depth = depth;
    }



    public void setNumberOfTreasures(Float numberOfTreasures) {
        this.numberOfTreasures = numberOfTreasures > 0 ? numberOfTreasures : this.numberOfTreasures;
    }

    @Override
    public String toString() {
        return "DragonCave{" +
                "depth=" + depth +
                ", numberOfTreasures=" + numberOfTreasures +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
