package com.cepeda.utiities;

import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author CyborgK27
 */
public class DateUtils {
    public static LocalDate convertJDateChooserToLocalDate(JDateChooser jDateChooser) {
        Date date = jDateChooser.getDate();
        return convertDateToLocalDate(date);
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
