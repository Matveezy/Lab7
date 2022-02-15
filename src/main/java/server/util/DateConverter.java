package server.util;

import java.sql.Date;
import java.time.LocalDate;

public final class DateConverter {
    private DateConverter() {
    }

    public static Date convertToDate(LocalDate localDate){
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        return date;
    }

    public static LocalDate convertToLocalDate(Date date){
        return date.toLocalDate();
    }
}
