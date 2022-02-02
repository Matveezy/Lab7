package lib.collection;

import java.io.Serializable;

public enum Color implements Serializable {
    GREEN("green"),
    RED("red"),
    BLUE("blue"),
    ORANGE("orange"),
    BROWN("brown");
    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
