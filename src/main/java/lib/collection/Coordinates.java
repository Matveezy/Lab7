package lib.collection;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Integer x; //Поле не может быть null
    private Double y; //Значение поля должно быть меньше -212, Поле не может быть null

    public Coordinates(Integer x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y <= -212 ? y : this.y;
    }

    public Integer getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


}
