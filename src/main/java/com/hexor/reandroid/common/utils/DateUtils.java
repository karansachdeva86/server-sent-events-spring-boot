package com.hexor.reandroid.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateUtils {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    public static Timestamp now() {
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            return new Timestamp(sdf.parse(sdf.format(cal.getTime())).getTime());
        } catch (ParseException pe) {
            throw new RuntimeException(pe.getMessage());
        }
    }

}