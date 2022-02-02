package lib.utils;

import lib.collection.Dragon;

public class DateParser {

    public int getYear(Dragon dragon) {
        String date = dragon.getCreationDate().toString();
        String year = date.split("-")[0];
        return Integer.parseInt(year);
    }

    public int getMonth(Dragon dragon) {
        String date = dragon.getCreationDate().toString();
        String month = date.split("-")[1];
        return Integer.parseInt(month);
    }

    public int getDay(Dragon dragon) {
        String date = dragon.getCreationDate().toString();
        String day = date.split("-")[2];
        return Integer.parseInt(day);
    }
}
