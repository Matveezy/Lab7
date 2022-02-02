package lib.file;

import lib.collection.Dragon;
import lib.collectionworker.CollectionManager;

public class CollectionValidator {
    private CollectionManager collectionManager;

    public CollectionValidator(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean validateId(Dragon dragon) {
        if (dragon.getId() <= 0) {
            System.out.println("Id не может быть меньше или равно нулю!");
            return false;
        }
        return true;
    }

    public boolean validateCoordinates(Dragon dragon) {
        try {
            if (dragon.getCoordinates().getX().equals("null")) {
                System.out.println("Поле coordinate_x не может быть null!");
                return false;
            }
            if (dragon.getCoordinates().getY().equals("null")) {
                System.out.println("Поле coordinate_y не может быть null!");
                return false;
            } else if (dragon.getCoordinates().getY() >= -212) {
                System.out.println("Поле coordinate_y должно быть меньше -212!");
                return false;
            }
        } catch (NullPointerException e) {
            System.out.println("Некорректно задано поле coordinate!");
            return false;
        }
        return true;
    }

    public boolean validateAge(Dragon dragon) {
        try {
            if (dragon.getAge() <= 0) {
                System.out.println("Значение age должно быть больше или равно нуля!");
                return false;
            }
        } catch (NullPointerException e) {
            dragon.setAge(null);
            return true;
        }
        return true;
    }

    public boolean validateDate(Dragon dragon) {
        try {
            /**
             * как оказалось, парсить дату и проверять каждое значение - бесполезное занятие , так как при любом некорректном вооде дня и месяца
             * JAXB сразу бросает NPE. Но валидация для года  тут нужна, так как jaxb не отслеживает этот момент!
             * Оставил валидатор для месяца и дня просто для корректности
             */
            String[] parseDate = dragon.getCreationDate().toString().split("-");
            Integer year = Integer.parseInt(parseDate[0]);
            Integer month = Integer.parseInt(parseDate[1]);
            Integer day = Integer.parseInt(parseDate[2]);

            if (year <= 0 || year > 2022) {
                System.out.println("Некорректно указан год!");
                return false;
            }
            if (month <= 0 || month > 12) {
                System.out.println("Некорректно указан месяц!");
                return false;
            }
            if (day <= 0 || day >= 31) {
                System.out.println("Некорректно указан день!");
                return false;
            }

        } catch (NullPointerException e) {
            System.out.println("Некорректно задано поле creation_Date!");
            return false;
        }
        return true;
    }

    public boolean validateCave(Dragon dragon) {
        try {
            if (dragon.getCave().getNumberOfTreasures() <= 0) {
                System.out.println("Значение number_of_treasure должно быть больше нуля!");
                return false;
            }
        } catch (NullPointerException e) {
            System.out.println("Значение number_of_treasure должно быть больше нуля!");
            return false;
        }
        return true;
    }

    public boolean validateColor(Dragon dragon) {
        if (dragon.getColor() == null) {
            System.out.println("Color не можеть быть задан null!");
            return false;
        }
        return true;
    }

    public boolean isThatIdContainsInCollection(Dragon dragon) {
        for (Dragon val : collectionManager.getDragons()) {
            if (val.getId() == dragon.getId()) {
                collectionManager.removeById(val.getId());
                return true;
            }
        }
        return false;
    }
}

