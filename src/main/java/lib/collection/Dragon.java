package lib.collection;

import lib.file.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "dragon")
@XmlType(propOrder = {"id", "name", "coordinates", "creationDate", "age", "color", "type", "character", "cave"})
public class Dragon implements Comparable<Dragon>, Serializable {


    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Coordinates coordinates; //Поле не может быть null

    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private Integer age; //Значение поля должно быть больше 0, Поле может быть null

    private Color color; //Поле не может быть null

    private DragonType type; //Поле может быть null

    private DragonCharacter character; //Поле может быть null

    private DragonCave cave; //Поле не может быть null

    /**
     * Конструктор без указания id элемента
     *
     * @param name
     * @param coordinates
     * @param creationDate
     * @param age
     * @param color
     * @param type
     * @param character
     * @param cave
     */
    public Dragon(long id, String name, Coordinates coordinates, LocalDate creationDate, Integer age, Color color, DragonType type, DragonCharacter character, DragonCave cave) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
    }

    public Dragon() {

    }

    public Dragon(String name, Coordinates coordinates, LocalDate creationDate, Integer age, Color color, DragonType type, DragonCharacter character, DragonCave cave) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
    }
    public Dragon(long id) {
        this.id = id;
    }

    @XmlAttribute
    public void setId(long id) {
        this.id = id;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @XmlElement
    public void setAge(Integer age) {
        this.age = age;
    }

    @XmlElement
    public void setColor(Color color) {
        this.color = color;
    }

    @XmlElement
    public void setType(DragonType type) {
        this.type = type;
    }

    @XmlElement
    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }



        @XmlElement
    public void setCave(DragonCave cave) {
        this.cave = cave;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Integer getAge() {
        return age;
    }

    public Color getColor() {
        return color;
    }

    public DragonType getType() {
        return type;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public DragonCave getCave() {
        return cave;
    }

    @Override
    public String toString() {
        return "Dragon{" +
                " id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", color=" + color +
                ", type=" + type +
                ", character=" + character +
                ", cave=" + cave +
                '}';
    }

    @Override
    public int compareTo(Dragon o) {
        Integer dragonAge;

        if (o.getAge() == null) dragonAge = 0;
        else dragonAge = o.getAge();

        Double comparingFlag = (this.coordinates.getX() * 10 + this.coordinates.getY() * 10 + this.age * 5 + this.cave.getNumberOfTreasures() * 10) - (o.coordinates.getX() * 10 + o.coordinates.getY() * 10 + dragonAge * 5 + o.cave.getNumberOfTreasures() * 10);
        if (comparingFlag > 0) return 1;
        if (comparingFlag < 0) return -1;
        return 0;
    }
}
