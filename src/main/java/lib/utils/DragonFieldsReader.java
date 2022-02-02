package lib.utils;

import lib.collection.*;
import lib.userio.UserIO;

import java.util.Scanner;

public class DragonFieldsReader {

    Scanner scanner = new Scanner(System.in);
    private UserIO userIO = new UserIO(scanner);

    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }

    public String readName() {
        String str;
        while (true) {
            userIO.printCommandText("Введите имя дракона (не null) :\n");
            str = userIO.getLine();
            if (str.trim().equals("")) {
                userIO.printErrorText("Имя не может быть пустой строкой!\n");
                continue;
            }
            if (str.trim().equals("null")) {
                userIO.printErrorText("Поле name не может быть null!\n");
                continue;
            }
            return str;
        }
    }


    public Integer readCoordinateX() {
        Integer x;

        while (true) {
            try {
                userIO.printCommandText("Введите координату X (не может быть null) : \n");
                String str = userIO.getLine();
                if (str.trim().equals("")) {
                    userIO.printErrorText("Коодината X не может быть задана пустой строкой!\n");
                }
                x = Integer.parseInt(str.trim());
                return x;
            } catch (NumberFormatException e) {
                userIO.printErrorText("Координата не можеть быть задана не числом!\n");
            }
        }
    }

    public Double readCoordinateY() {
        Double y;
        while (true) {
            try {
                userIO.printCommandText("Введите координату Y (не может быть null и должна быть меньше -212) : \n");
                String str = userIO.getLine();
                if (str.trim().equals("")) {
                    userIO.printErrorText("Коодината Y не может быть задана пустой строкой!\n");
                }
                y = Double.parseDouble(str.trim());
                if (y > -212) {
                    throw new IllegalArgumentException();
                }
                return y;
            } catch (NumberFormatException e) {
                userIO.printErrorText("Координата не можеть быть задана не числом!\n");
            } catch (IllegalArgumentException e2) {
                userIO.printErrorText("Координата не можеть быть задана числом больше -212!\n");
            }
        }

    }

    public Integer readAge() {
        Integer age;

        while (true) {
            try {
                userIO.printCommandText("Введите возраст вашего дракона (должен быть больше нуля, может быть равен null) :\n");
                String str = userIO.getLine();
                if (str.trim().equals("")) {
                    userIO.printErrorText("Возраст не может быть задана пустой строкой!\n");
                }
                if (str.trim().equals("null")) {
                    age = null;
                    return age;
                }
                age = Integer.parseInt(str.trim());
                if (age <= 0) {
                    throw new IllegalArgumentException();
                }
                return age;
            } catch (NumberFormatException e) {
                userIO.printErrorText("Возраст не можеть быть задан не числом!\n");
            } catch (IllegalArgumentException e2) {
                userIO.printErrorText("Возраст не можеть быть задан числом меньше нуля!\n");
            }
        }
    }


    public Color readColor() {
        Color color;
        userIO.printCommandText("Выберите цвет из данного списка (не можеть быть null) :\n");
        for (Color colors : Color.values()) {
            System.out.println(colors.name());
        }

        while (true) {
            try {
                userIO.printCommandText("Цвет вашего дракона :\n");
                color = Color.valueOf(userIO.getLine().trim().toUpperCase());
                return color;
            } catch (IllegalArgumentException e) {
                userIO.printErrorText("Значение введенной константы не представлено в перечислении Color\n");
            }
        }
    }

    public DragonType readDragonType() {
        DragonType dragonType;
        userIO.printCommandText("Все возможные типы драконов:(может быть null)\n");
        for (DragonType val : DragonType.values()) {
            System.out.println(val.name());
        }
        while (true) {
            try {
                userIO.printCommandText("Выберите тип вашего дракона:\n");
                String input = userIO.getLine().trim();
                if (input.equals("null")) {
                    dragonType = null;
                    return dragonType;
                }
                dragonType = DragonType.valueOf(input.toUpperCase());
                return dragonType;
            } catch (IllegalArgumentException e) {
                userIO.printErrorText("Значение введенной константы не представлено в перечислении DragonType\n");
            }
        }
    }

    public DragonCharacter readDragonCharacter() {
        DragonCharacter dragonCharacter;
        userIO.printCommandText("Все возможные типы характеров драконов(может быть null) :\n");
        for (DragonCharacter val : DragonCharacter.values()) {
            System.out.println(val.name());
        }
        while (true) {
            try {
                userIO.printCommandText("Выберите характер вашего дракона :\n");
                String input = userIO.getLine().trim();
                if (input.equals("null")) {
                    dragonCharacter = null;
                    return dragonCharacter;
                }
                dragonCharacter = DragonCharacter.valueOf(input.toUpperCase());
                return dragonCharacter;
            } catch (IllegalArgumentException e) {
                userIO.printErrorText("Значение введенной константы не представлено в перечислении DragonCharacter\n");
            }
        }
    }

    public DragonCave readDragonCave() {
        return new DragonCave(readCaveDepth(), readNumberOfTreasures());
    }

    public Float readCaveDepth() {
        Float depth;
        userIO.printCommandText("Введите глубину пещеры вышего дракона (не можеть быть null) :\n");
        while (true) {
            try {
                String str = userIO.getLine();
                if (str.trim().equals("")) {
                    userIO.printErrorText("Глубина пещеры не может быть пустой строкой!\n");
                }
                depth = Float.parseFloat(str.trim());
                return depth;
            } catch (NumberFormatException e) {
                userIO.printErrorText("Глубина пещеры не можеть быть задана не числом!\n");
            }
        }
    }

    public Float readNumberOfTreasures() {
        Float numberOfTreasures;
        userIO.printCommandText("Введите количество сокровищ (не можеть быть null и должно быть больше 0) :\n");
        while (true) {
            try {
                String str = userIO.getLine();
                if (str.trim().equals("")) {
                    System.err.println("Количество сокровищ не может быть пустой строкой!");
                }
                numberOfTreasures = Float.parseFloat(str.trim());
                if (numberOfTreasures <= 0) {
                    System.err.println("Количество сокровищ должно быть больше нуля!");
                    continue;
                }
                return numberOfTreasures;
            } catch (NumberFormatException e) {
                System.out.println("Количество не можеть быть задано не числом!");
            }
        }
    }
}
